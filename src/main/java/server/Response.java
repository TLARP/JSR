package server;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by hzwangqiqing on 2016/10/19.
 */
public class Response implements ServletResponse{
    private Request request;

    private OutputStream outputStream;

    private int BUFFER_SIZE = 4096;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        byte[] array = new byte[BUFFER_SIZE];

        File file = new File(HttpServier.resourceDir, request.getURI());
        System.out.println(HttpServier.resourceDir+request.getURI());

        FileInputStream fis = null;

        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                int ch = fis.read(array, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(array, 0, ch);
                    ch = fis.read(array, 0, BUFFER_SIZE);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String errorMessage="HTTP/1.1 404 File not Found\r\n" +
                    "Content-Type text/html\r\n" +
                    "Content-Length 23\r\n" +
                    "\r\n" +
                    "<h1>File not Found</h1>";
            try {
                outputStream.write(errorMessage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {

            }
        }

        try {
            if(fis!=null){
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("not have this file");
            e.printStackTrace();
        }
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        PrintWriter out=new PrintWriter(outputStream,true);
        return out;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentLengthLong(long l) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
