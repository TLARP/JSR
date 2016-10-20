package server;

/**
 * Created by hzwangqiqing on 2016/10/19.
 */
public class StaticResourceProcessor {
    public void process(Request request,Response response){

        response.sendStaticResource();

    }
}
