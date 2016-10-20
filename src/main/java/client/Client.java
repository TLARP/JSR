package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by hzwangqiqing on 2016/10/18.
 */
public class Client {

    private Socket clientSocket;

    public Client() {
        try {
            clientSocket = new Socket("127.0.0.1",8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            OutputStream outputStream=clientSocket.getOutputStream();
            boolean autoFlush=true;
            //构造输出字符流对象
            PrintWriter out=new PrintWriter(outputStream,autoFlush);
            //构造输入字符流对象
            BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //套接字输出HTTP HEAD
            out.println("GET /sample/ HTTP/1.1");
            out.println("Host: localhost:8080");
            out.println("Connection: Close");
            out.println();

            StringBuffer stringBuffer=new StringBuffer(8096);

            boolean loop=true;

            while(loop){
                if(in.ready()){
                    int i=0;
                    while(i!=-1){
                        i=in.read();
                        stringBuffer.append((char)i);
                    }
                    loop=false;
                }
                Thread.currentThread().sleep(50);
            }
            System.out.println(stringBuffer.toString());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client=new Client();
        client.start();
    }
}
