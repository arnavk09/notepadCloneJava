package com.JavaNotepad;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

class editor extends JFrame implements ActionListener {
    JTextArea main_text_part;
    JFrame mainFrame;

    editor() {
        mainFrame = new JFrame("JavaPad: A Java Notepad Clone");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        // Text Area
        main_text_part = new JTextArea();

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // menu for FILE
        JMenu filemenu = new JMenu("File");

        //  menu items
        JMenuItem mainmenu1 = new JMenuItem("New");
        JMenuItem mainmenu2 = new JMenuItem("Open");
        JMenuItem mainmenu3 = new JMenuItem("Save");
        JMenuItem mainmenu4 = new JMenuItem("Print");

        // action listener
        mainmenu1.addActionListener(this);
        mainmenu2.addActionListener(this);
        mainmenu3.addActionListener(this);
        mainmenu4.addActionListener(this);

        filemenu.add(mainmenu1);
        filemenu.add(mainmenu2);
        filemenu.add(mainmenu3);
        filemenu.add(mainmenu4);

        //menu for EDIT
        JMenu editMenu = new JMenu("Edit");

        // menu items
        JMenuItem cut_option = new JMenuItem("Cut");
        JMenuItem copy_option = new JMenuItem("Copy");
        JMenuItem paste_option = new JMenuItem("Paste");
        //action listener
        cut_option.addActionListener(this);
        copy_option.addActionListener(this);
        paste_option.addActionListener(this);

        editMenu.add(cut_option);
        editMenu.add(copy_option);
        editMenu.add(paste_option);

        JMenuItem closeOption = new JMenuItem("close");

        closeOption.addActionListener(this);

        menuBar.add(filemenu);
        menuBar.add(editMenu);
        menuBar.add(closeOption);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(main_text_part);
        mainFrame.setSize(600, 400);
        mainFrame.show(true);
    }

    public void actionPerformed(ActionEvent e) {
        String obtainOption = e.getActionCommand();

        if (obtainOption.equals("Cut")) {
            main_text_part.cut();
        } else if (obtainOption.equals("Copy")) {
            main_text_part.copy();
        } else if (obtainOption.equals("Paste")) {
            main_text_part.paste();
        } else if (obtainOption.equals("Save")) {
            JFileChooser handleFile = new JFileChooser("c:");
            int area = handleFile.showSaveDialog(null);

            if (area == JFileChooser.APPROVE_OPTION) {
                File saveFile = new File(handleFile.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(saveFile, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(main_text_part.getText());
                    w.flush();
                    w.close();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(mainFrame, e1.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(mainFrame, "Select an OPTION!");
        } else if (obtainOption.equals("Print")) {
            try {
                main_text_part.print();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(mainFrame, exc.getMessage());
            }
        } else if (obtainOption.equals("Open")) {
            JFileChooser chooser;
            chooser = new JFileChooser("c:");
            int r = chooser.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File files = new File(chooser.getSelectedFile().getAbsolutePath());

                try {
                    String tring = "", def = "";
                    FileReader bre_ad = new FileReader(files);
                    BufferedReader br = new BufferedReader(bre_ad);
                    def = br.readLine();
                    while ((tring = br.readLine()) != null) {
                        def = def + "\n" + tring;
                    }
                    main_text_part.setText(def);
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(mainFrame, e2.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(mainFrame, "Operation Cancelled");
        } else if (obtainOption.equals("New")) {
            main_text_part.setText("");
        } else if (obtainOption.equals("close")) {
            System.exit(0);
        }
    }
    public static void main(String args[]) {
        editor renderMain = new editor();
    }
}