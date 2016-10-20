package server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hzwangqiqing on 2016/10/19.
 */
public class HttpServier {
    //shutdown uri
    public static final String shutdownCommand = "/shutdown";

    //resources dir
    public static final String resourceDir = System.getProperty("user.dir") + File.separator + "/webroot";

    //class path
    public static final String classDir=System.getProperty("user.dir")+File.separator+"/target/classes";


    //start wait client socket
    public void await() {
        //init null not forget
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (!isShutdown) {
            try {
                //accept a socket
                Socket socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);

                Response response = new Response(outputStream);

                //send static resource
                response.setRequest(request);

                if(!request.getURI().startsWith("/servlet/")){
                    StaticResourceProcessor staticResourceProcessor=new StaticResourceProcessor();
                    staticResourceProcessor.process(request,response);
                }else{
                    ServletProcessor serveletProcessor=new ServletProcessor();
                    serveletProcessor.process(request,response);
                }

                socket.close();

                //当接受到shutdown命令的时候我们就停止服务
                if(request.getURI().equals(shutdownCommand)){
                    isShutdown=true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //accept shutdown command will be set false
    private boolean isShutdown = false;


    public static void main(String[] args) {
        HttpServier httpServer = new HttpServier();
        httpServer.await();
    }
}
