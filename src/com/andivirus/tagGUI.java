package com.andivirus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public tagGUI() {
        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource()== pathButton) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setVisible(true);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("tagGUI");
        frame.setContentPane(new tagGUI().tagPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
