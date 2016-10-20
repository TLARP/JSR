package server;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by hzwangqiqing on 2016/10/19.
 */
public class ServletProcessor {
    public void process(Request request,Response response){
        String uri=request.getURI();
        String servletName=uri.substring(uri.lastIndexOf("/")+1);

        //类加载器
        URLClassLoader urlClassLoader=null;

        URL[] urls=new URL[1];

        URLStreamHandler urlStreamHandler=null;

        String filePath=HttpServier.classDir;



        File file=new File(filePath);

        try {
            System.out.println(file.getCanonicalPath()+File.separator);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            String responsity=(new URL("file",null,file.getCanonicalPath())+File.separator).toString();
            urls[0]=new URL(null,responsity,urlStreamHandler);
            urlClassLoader=new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass=null;
        try {
            myClass=urlClassLoader.loadClass("server."+servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        try {
            Servlet servlet=(Servlet)myClass.newInstance();
            servlet.service((ServletRequest) request,(ServletResponse) response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
