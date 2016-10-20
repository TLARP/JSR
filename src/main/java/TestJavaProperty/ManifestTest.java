package TestJavaProperty;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.net.Socket;

/**
 * Created by hzwangqiqing on 2016/10/8.
 */
public class ManifestTest {
    public static void main(String[] args) {
        String jarFilePath="D:\\Program Files (x86)\\apache-tomcat-8.0.36\\webapps\\yixunxi.war";
        try {
            JarFile jarFile=new JarFile(jarFilePath);

            Manifest mainfest=jarFile.getManifest();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
