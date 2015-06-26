package com.andivirus;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import com.beaglebuddy.mp3.MP3;
import com.beaglebuddy.id3.enums.PictureType;

import javax.imageio.ImageIO;


public class Main {
    private String path;
    private String artist;
    private String album;
    private boolean cover;

    public static void main(String[] args){
        //System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());

        if(args.length != 0) {
            Main main = new Main();
            main.setOperations(args);
            main.execute();
        }
        else{
            System.out.println("Give args");
            String[] err = new String[1];
            err[0] = "-h";
            Main main = new Main();
            main.setOperations(err);
        }
    }

    public void execute(){
        if(artist != null){
            setArtist();
        }

        if(album != null){
            setAlbum();
        }

        if(cover){
            setCover();
        }
    }

    public void setOperations(String[] args){
        for(String s: args){
            if(s.toLowerCase().contains("-op=")){
                path = s.replace("-op=", "");
                System.out.println(path);
            }

            if(s.toLowerCase().contains("-p=")){
                try{
                    path = Paths.get(new PropFileHandler().getPropValues("musiclocation")).toAbsolutePath().normalize().toString() + s.replace("-p=", "");
                    System.out.println(path);
                }   catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(s.toLowerCase().contains("-ar=")){
                artist = s.replace("-ar=", "");
                System.out.println(artist);
            }
            if(s.toLowerCase().contains("-al=")){
                album = s.replace("-al=", "");
                System.out.println(album);
            }

            if(s.toLowerCase().contains("-c")){
                cover = true;
            }

            if(s.toLowerCase().contains("--setmusicpath=")){
                try {
                    new PropFileHandler().setPropValues("musiclocation", s.replace("--setmusicpath=", ""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(s.toLowerCase().contains("--getmusicpath")){
                try {
                    System.out.println("Default Folder: " + new PropFileHandler().getPropValues("musiclocation"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(s.toLowerCase().contains("-h")){
                System.out.println("Available Options:");
                System.out.println("-op=<PATH> takes the full path of a folder, in case its not in default music folder");
                System.out.println("-p=<PATH> takes the folder name, must be in default music folder or an underdirectory");
                System.out.println("-ar=<Artist> passes on the artist, if its necessary to change");
                System.out.println("-al=<ALBUM> passes on the album, if its necessary to change");
                System.out.println("-c adds an image from the clipboard to the id3tag");
                System.out.println("--setmusicpath=<PATH> changes the default music folder");
                System.out.println("--getmusicpath prints out the default music path");
                System.out.println("-h prints this message");
            }

            //TODO: //MAYBE: add option for uploading to ftp
        }
    }

    public void setArtist(){
        File file = new File(path);

        for(File f: file.listFiles(new FilenameFilter(){
            @Override
        public boolean accept(File dir, String name){
                return name.toLowerCase().endsWith(".mp3");

            }
        })){
            try{
                MP3 mp3 = new MP3(f);
                mp3.setLeadPerformer(artist);
                mp3.save();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void setAlbum(){
        File file = new File(path);

        for(File f: file.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name){
                return name.toLowerCase().endsWith(".mp3");

            }
        })){
            try{
                MP3 mp3 = new MP3(f);
                mp3.setAlbum(album);
                mp3.save();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void setCover(){
        File file = new File(path);

        for(File f: file.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name){
                return name.toLowerCase().endsWith(".mp3");

            }
        })){
            try{

                BufferedImage image = getImageFromClipboard();
                if(image != null) {

                    MP3 mp3 = new MP3(f);
                    File picture = new File("albumpicture");
                    ImageIO.write(getImageFromClipboard(), "png", picture);
                    mp3.setPicture(PictureType.FRONT_COVER, picture);
                    picture.delete();
                    mp3.save();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public BufferedImage getImageFromClipboard(){
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if(transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)){
            try {
                return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("There is no image in the clipboard");
        }
        return null;
    }

}
