/**
 * songApplicationGui.java
 * 
 * This file contains the main class for the GUI song application
 * 
 * @author Ashton Dunderdale
 * Date: September 26, 2023 
 * 
 */

package songapplicationgui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SongApplicationGui {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        frame.setTitle("Song Application");
        frame.setSize(1024, 768); // this resolution is compatible for most people
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminates program when user exits application
        
        ImageIcon image = new ImageIcon("logo.png"); // these lines..
        frame.setIconImage(image.getImage()); // do not work.. for some reason.
        
        frame.getContentPane().setBackground(new Color(50,50,50)); // background colour
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem addSongButton = new JMenuItem("Add Song");
        JMenuItem removeSongButton = new JMenuItem("Remove Song");
        fileMenu.add(addSongButton);
        fileMenu.add(removeSongButton);
        
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewSongsButton = new JMenuItem("View Songs");
        JMenuItem viewTopSongsButton = new JMenuItem("View Top Songs");
        viewMenu.add(viewSongsButton);
        viewMenu.add(viewTopSongsButton);
        
        JMenu aboutMenu = new JMenu("About");
        JMenuItem helpButton = new JMenuItem("Help");
        JMenuItem guideButton = new JMenuItem("Application Guide");
        aboutMenu.add(helpButton);
        aboutMenu.add(guideButton);
        
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
        
        frame.setJMenuBar(menuBar);
        
        frame.setVisible(true);
        
    }
}
