package com.andivirus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by andrey on 07.07.15.
 */
public class tagGUI {
    private JTextField pathTextField;
    private JButton pathButton;
    private JTextField artistTextField;
    private JTextField albumTextField;
    private JCheckBox coverCheckBox;
    private JLabel coverImageLabel;
    private JPanel tagPanel;
    private JButton runButton;
    private Main tagger;

    public tagGUI() {
        tagger = new Main();
        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnval = chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                if(returnval==JFileChooser.APPROVE_OPTION) {
                    pathTextField.setText(file.getAbsolutePath());
                    runButton.setEnabled(true);
                }
                else{
                    pathTextField.setText("Du musst nen Pfad auswaehlen du Nase");
                }


            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String path = pathTextField.getText();
                String artist = artistTextField.getText();
                String album = albumTextField.getText();
                String argumentstring = path;


                if(!artist.isEmpty()){
                    argumentstring = argumentstring + " -ar=" + artist;
                }
                if(!album.isEmpty()){
                    argumentstring = argumentstring + " -al=" + album;
                }
                if(coverCheckBox.isEnabled()){
                    argumentstring = argumentstring + " -c";
                }
                String[] strings = new String[5];
                strings = argumentstring.split(" -");
                for (String s: strings){
                    System.out.println(s);
                }
                //tagger.setOperations();
            }
        });
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux") || System.getProperty("os.name").toLowerCase().contains("aix")) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("tagGUI");
        frame.setContentPane(new tagGUI().tagPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
