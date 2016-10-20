package server;

import javax.servlet.*;
import  javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hzwangqiqing on 2016/10/19.
 */
public class PrimitiveServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet init!!!");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("running in servlet service!!!");
        PrintWriter out=servletResponse.getWriter();
        out.println("hello every one!!!!");
        out.println("violets are blue");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("running in destory!!!");
    }
}
