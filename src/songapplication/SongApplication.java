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
    
    // constants for menu options, removes unessecary, unwanted "magic numbers"
    private static final int EXIT_OPTION = 0;
    private static final int ADD_SONG_OPTION = 1;
    private static final int REMOVE_SONG_OPTION = 2;
    private static final int VIEW_SONGS_OPTION = 3;
    private static final int VIEW_TOP_SONGS_OPTION = 4;
    
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
        
        while(true){
            int menuChoice = getUserMenuChoice();
            
            switch (menuChoice) {
                case ADD_SONG_OPTION -> addSong();
                case REMOVE_SONG_OPTION -> removeSong();
                case VIEW_SONGS_OPTION -> viewSongs();
                case VIEW_TOP_SONGS_OPTION -> viewTopSongs();
                case EXIT_OPTION -> exitApplication();
            }
        }
    }

     /**
     * Get the user menu choice and validate it
     * 
     * @return the valid menu choice.
     */  
    public static int getUserMenuChoice(){
        boolean validMenuChoice = false;
        int menuChoice = 0;
        
        while(!validMenuChoice){
            displayMenu();
            
            if (userInput.hasNextInt()){
                menuChoice = userInput.nextInt();
                userInput.nextLine();
                
                switch (menuChoice){
                    case ADD_SONG_OPTION, REMOVE_SONG_OPTION, VIEW_SONGS_OPTION, VIEW_TOP_SONGS_OPTION, EXIT_OPTION -> validMenuChoice = true;
                    default -> System.out.print("Invalid Choice. Please enter a valid menu option: (0 - 4)\n\n");         
                }
            } else{
                System.out.println("Invalid Type. Please enter a valid number.\n");
                userInput.nextLine();
            }
        }
        return menuChoice;
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
    
    /**
     * Exit the application 
     */
    public static void exitApplication(){
        System.out.println("Thank you for using the song application. The Program will now close.");
        userInput.close();
        System.exit(0);
    }
}
