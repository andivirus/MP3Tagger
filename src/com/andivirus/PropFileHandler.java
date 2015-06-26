package com.andivirus;

import java.io.*;
import java.util.Properties;

/**
 * Created by andiv_000 on 26.06.2015.
 */
public class PropFileHandler {
    Properties prop;
    InputStream inputStream;
    OutputStream outputStream;
    String propFileName = "config.properties";

    public PropFileHandler() throws IOException {
        prop = new Properties();
        inputStream = new FileInputStream(propFileName);

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
        try {
            outputStream = new FileOutputStream(propFileName);
            prop.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
