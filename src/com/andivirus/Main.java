package com.andivirus;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.beaglebuddy.mp3.MP3;
import com.beaglebuddy.id3.enums.Genre;
import com.beaglebuddy.id3.enums.PictureType;

import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {
    private String path;
    private String artist;
    private String album;

    public static void main(String[] args){
        if(args.length != 0) {
            Main main = new Main();
            main.setOperations(args);
        }
        else{
            System.out.println("Give args");
        }
    }

    public void setOperations(String[] args){
        for(String s: args){
            if(s.toLowerCase().contains("-p=")){
                path = s.replace("-p=", "");
                System.out.println(path);
            }
            if(s.toLowerCase().contains("-ar=")){
                artist = s.replace("-ar=", "");
                System.out.println(artist);
            }
            if(s.toLowerCase().contains("-al=")){
                album = s.replace("-al=", "");
                System.out.println(album);
            }

            if(s.toLowerCase().contains("-c=")){
                //TODO: Fill in stuff for album Cover
            }

            //TODO: //MAYBE: add option for uploading to ftp
        }
    }
    /*
    public void testreadartist(){
        File file = new File(fpath);


            for(File f: file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name){
                    return name.toLowerCase().endsWith(".mp3");
                }
            })){
                try {
                    MP3 mp3 = new MP3(f);
                    System.out.println(mp3.getTitle());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //System.out.println(f.getAbsolutePath());
            }



    }
    */
}
