package com.andivirus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by andiv_000 on 26.06.2015.
 */
public class PropFileHandler {
    Properties prop;
    InputStream inputStream;

    public PropFileHandler() throws IOException {
        prop = new Properties();
        String propFileName = "config.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if(inputStream != null){
            prop.load(inputStream);
        }else{
            throw new FileNotFoundException("propfile not found in classpath");

        }
    }

    public String getPropValues(String key){
        String result;
        result = prop.getProperty(key);
        return result;
    }

    public void setPropValues(String key, String value){
        prop.setProperty(key, value);

    }
}
