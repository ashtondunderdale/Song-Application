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
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

/**
 * This class defines constants to be used in the menu interface,
 * initialises Scanner class and ArrayList to store song objects
 * 
 */
public class SongApplication {
   
    // ArrayList to store song objects
    private static final List<Song> songs = new ArrayList<>();
   
    // constants for menu options, removes unessecary, unwanted "magic numbers"
    private static final int EXIT_OPTION = 0;
    private static final int ADD_SONG_OPTION = 1;
    private static final int REMOVE_SONG_OPTION = 2;
    private static final int VIEW_SONGS_OPTION = 3;
    private static final int VIEW_TOP_SONGS_OPTION = 4;
    
    // Scanner for user input
    static Scanner userInput = new Scanner(System.in);
    
    
    /**
     * The entry point of the program. Initialises the list of songs,
     * displays menu, calls initial method..
     * 
     * @param args (not used).
     */
    public static void main(String[] args) {
        
        songs.add(new Song("Let It Happen", "Tame Impala", "4:16", 108998497));
//        songs.add(new Song("Poker Face", "Lady Gaga", "3:34", 1210515591));
//        songs.add(new Song("Summertime Sadness", "Lana Del Ray", "4:26", 687585745));
//        songs.add(new Song("The Less I Know The Better", "TameImpala", "3:37", 557449061));
//        songs.add(new Song("Bohemian Rhapsody", "Queen", "5:55", 758473934));
//        songs.add(new Song("Rolling in the Deep", "Adele", "3:38", 123955524));
//        songs.add(new Song("Billie Jean", "Micheal  Jackson", "4:54", 98746375));
//        songs.add(new Song("Stairway to Heaven", "Led Zeppelin", "8:02", 429483942));
//        songs.add(new Song("Shape of You", "Ed Sheeran", "3:353", 77642142));
//        songs.add(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", "4:31", 223454389));
        
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
        System.out.println("\tAdd Songs\n\nEnter the name of the song you would like to add.");
        String songName = userInput.nextLine();
        
        System.out.println("\nEnter the Artist name for this song.");
        String artistName = userInput.nextLine();
        
        System.out.println("\nEnter the Duration of the song in this format: 2:15");
        String durationString = userInput.nextLine();
        
        // initialise random class
        Random rand = new Random();
        int playCount = rand.nextInt(1000000000); // upper bound: 1 billion
                
        songs.add(new Song(songName, artistName, durationString, playCount));
        System.out.println(returnToMenuStatement());
    }   
    
    
     /**
     * Removes a chosen song 
     */   
    public static void removeSong(){
        returnEmptySongStatement();
        
        System.out.println("\tRemove Songs\n\nEnter the name of the song you would like to remove.");
        
        String songToRemove = userInput.nextLine();
        List<Song> songsToRemove = new ArrayList<>(); // initialise a "removed songs" array for storing and then deletion
        
        for (Song song : songs){
            if (song.songName.equals(songToRemove)){
                songsToRemove.add(song);
            }
        }
        
        if (!songsToRemove.isEmpty()) { // if array is not empty, remove all items
            songs.removeAll(songsToRemove);
            System.out.println("\nThe Song " + songToRemove + " has been removed.\n" + returnToMenuStatement());
            userInput.nextLine();
        } else{
            System.out.println("\nThe song " + songToRemove + " could not be found.\n" + returnToMenuStatement());
            userInput.nextLine();
        }
    }
    
    
     /**
     * Shows a list of all songs
     */   
    public static void viewSongs(){
        returnEmptySongStatement();
                
        System.out.println("\tView Songs\n");
        
        int songCounter = 0;
        int songCounterLength = 4;
        
        for (Song song : songs) {
            songCounter++;
            String formattedCounter = String.format("%-" + songCounterLength + "s", songCounter);
            System.out.println(formattedCounter + song);
        }
        System.out.println(returnToMenuStatement());
        userInput.nextLine();
    }
    
    
     /**
     * Shows the top ten songs in order of play count
     */   
    public static void viewTopSongs(){
        returnEmptySongStatement();
        
        System.out.println("\tView Most Played Songs\n");
        
        int topSongCounter = 0;
        int songCounterLength = 3;
        
        List<Song> sortedSongs = new ArrayList<>(songs);
        Collections.sort(sortedSongs, (Song song1, Song song2) -> Integer.compare(song2.playCount, song1.playCount));
        
        for (Song song : sortedSongs) {
            topSongCounter++;
            String formattedCounter = String.format("%-" + songCounterLength + "s", + topSongCounter);
            System.out.println(formattedCounter + song);
            if (topSongCounter >= 10){
                break;
            }
        }
        System.out.println(returnToMenuStatement());
        userInput.nextLine();
    }
    
    
    /**
     * Exit the application 
     */
    public static void exitApplication(){
        System.out.println("Thank you for using the song application. The Program will now close.");
        userInput.close();
        System.exit(0);
    }
    
    /**
     * @return a string prompting the user to press enter to return to the menu
     */
    public static String returnToMenuStatement(){ // recursive
        String returnToMenuString = "\nPress the enter key to return to the menu";
        return returnToMenuString;
    }
    
    public static void returnEmptySongStatement(){ // recursive
        
        if (songs.isEmpty()){
            System.out.println("There are no songs stored, add songs first to use other features.\n" + returnToMenuStatement());
            userInput.nextLine();
            processMenuChoice();
        }
    }
}
