package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Configurations {
    private  static  Configurations instance = null;
    private Properties properties;

    private  Configurations(){
        properties = new Properties();
    }

    public void AddProperty(String property,String value ){
        this.properties.setProperty(property, value);
    }
    public static Configurations getInstance(){
        if(instance == null){
            instance = new Configurations();
        }
        return instance;
    }
    public void getProperty(String property) {
        properties.getProperty(property);
    }

}


