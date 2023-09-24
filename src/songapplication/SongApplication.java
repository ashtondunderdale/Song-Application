/**
 * initialSongProject.java
 * 
 * This file contains the main class for the song application.
 * 
 * @author Ashton Dunderdale
 * Date: September 24, 2023 
 * 
 */

package songapplication;
import java.util.Scanner;

public class SongApplication {
    
    // Scanner for user input
    static Scanner userInput = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        // calls initial method
        processMenuChoice(); 
    }
    
    /**
     * Process the user menu choice in a loop.
     */
    public static void processMenuChoice(){
    
    }

     /**
     * Get the user menu choice and validate it
     * 
     * @return the valid menu choice.
     */  
    public static int getUserMenuChoice(){
    
    }
    
     /**
     * Display the menu to the user
     */
    public static void displayMenu(){ // recursive
        System.out.println("""
                   \tMusic Player\n
                   Choose a menu option:\n
                   \t1. Add Song
                   \t2. Remove Song
                   \t3. View Songs
                   \t4. View Top Songs
                   \n\t0. Exit Application
                   """);
    }
    
    
     /**
     * Adds a chosen song, generates random playCount
     */   
    public static void addSong(){
    
    }
    
    
     /**
     * Removes a chosen song 
     */   
    public static void removeSong(){
    
    }
    
    
     /**
     * Shows a list of all songs
     */   
    public static void viewSongs(){
    
    }
    
    
     /**
     * Shows the top ten songs in order of play count
     */   
    public static void viewTopSongs(){
    
    }
}
