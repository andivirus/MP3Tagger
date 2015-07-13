package com.andivirus;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

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
    private JLabel progressLabel;
    private Main tagger;

    public tagGUI() {
        tagger = new Main();
        setCoverLabel();

        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnval = chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    pathTextField.setText(file.getAbsolutePath());
                    runButton.setEnabled(true);
                    progressLabel.setText("Ready");
                } else {
                    progressLabel.setText("No Path Specified");
                }


            }
        });

        try{
            Toolkit.getDefaultToolkit().getSystemClipboard().addFlavorListener(new FlavorListener() {
                @Override
                public void flavorsChanged(FlavorEvent e) {
                    setCoverLabel();
                }


            });
        }
        catch(IllegalArgumentException e){
            System.out.println("LEL");
        }

        coverImageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCoverLabel();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String path = pathTextField.getText();
                String artist = artistTextField.getText();
                String album = albumTextField.getText();
                LinkedList<String> stringLinkedList = new LinkedList<String>();
                String[] arguments = new String[4];

                stringLinkedList.add("-op=" + path);
                if (!artist.isEmpty()) {
                    stringLinkedList.add("-ar=" + artist);
                }
                if (!album.isEmpty()) {
                    stringLinkedList.add("-al=" + album);
                }
                if (coverCheckBox.isEnabled()) {
                    stringLinkedList.add("-c");
                }

                System.out.println("Printing arguments before passed");

                for (int i = 0; i < stringLinkedList.size(); i++) {
                    arguments[i] = stringLinkedList.get(i);
                }
                for (String s : arguments) {
                    System.out.println(s);
                }

                tagger.setOperations(arguments);
                tagger.execute();
                progressLabel.setText("Done");
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

    public void setCoverLabel(){
        coverImageLabel.setIcon(null);
        BufferedImage bufferedImage = tagger.getImageFromClipboard();
        if(bufferedImage != null) {
            ImageIcon img = new ImageIcon(bufferedImage);
            img.setImage(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            coverImageLabel.setText("");
            coverImageLabel.setIcon(img);
        }
        else{
            coverImageLabel.setText("No picture in Clipboard");
        }
    }

}
