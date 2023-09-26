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

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Color;

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
        
        frame.setVisible(true);
        
    }
}
