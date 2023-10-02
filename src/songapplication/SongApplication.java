/**
 * SongApplication.java
 * 
 * This file contains the main class for the song application
 * 
 * @author Ashton Dunderdale
 * Date: October 2, 2023 
 * 
 */

package songapplication;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * This class defines constants to be used in the menu interface,
 * It also defines constants for repeatedly used messages for the recursive message methods
 * initialises Scanner class and ArrayList to store song objects
 * 
 */
public class SongApplication 
{  
    // ArrayList to store song objects
    private static final List<Song> songs = new ArrayList<>();
    private static final List<String> searchHistory = new ArrayList<>();
   
    // constants for menu options and message methods, removes unessecary, unwanted "magic numbers"
    private static final int EXIT_OPTION = 0;
    private static final int ADD_SONG_OPTION = 1;
    private static final int REMOVE_SONG_OPTION = 2;
    private static final int VIEW_SONGS_OPTION = 3;
    private static final int VIEW_TOP_SONGS_OPTION = 4;
    private static final int SEARCH_SONGS_OPTION = 5;
    private static final int VIEW_SEARCH_HISTORY_OPTION = 6;
    private static final int PLAY_SONG_OPTION = 7;
    
    private static final String RETURN_TO_MENU_STRING = "\nPress the enter key to return to the menu";
    private static final String EXIT_MESSAGE = 
            "\nThank you for using the song application. The Program will now close.";
    
    private static final String EMPTY_SONG_STATEMENT = 
            "\nThere are no songs stored, add songs first to use other features.\n";

    static Scanner userInput = new Scanner(System.in);
    
    
    /**
     * The entry point of the program. Initialises the list of songs,
     * displays menu, calls initial starter method..
     * 
     * @param args (not used).
     */
    public static void main(String[] args)
    { 
        songs.add(new Song("Let It Happen", "Tame Impala", "4:16", 108998497));
        songs.add(new Song("Bad Romance", "Lady Gaga", "3:34", 1210515591));
        songs.add(new Song("Summertime Sadness", "Lana Del Ray", "4:26", 687585745));
        songs.add(new Song("The Less I Know The Better", "Tame Impala", "3:37", 557449061));
        songs.add(new Song("Smells Like Teen Spirit", "Nirvana", "5:55", 758473934));
        songs.add(new Song("Poker Face", "Lady Gaga", "3:38", 123955524));
        songs.add(new Song("Tribute", "Tenacious D", "4:52", 98746375));
        songs.add(new Song("Be Quiet And Drive", "Deftones", "8:02", 429483942));
        songs.add(new Song("Everlong", "Foo Fighters", "3:35", 77642142));
        songs.add(new Song("Change", "Deftones", "4:31", 223454389));
        
        // calls initial program starter method
        ProcessMenuChoice(); 
    }
    
    
    /**
     * Process the user menu choice in a loop.
     */
    public static void ProcessMenuChoice()
    {        
        while(true)
        {
            int menuChoice = GetUserMenuChoice();
            
            switch (menuChoice)
            {
                case ADD_SONG_OPTION -> AddSong();
                case REMOVE_SONG_OPTION -> RemoveSong();
                case VIEW_SONGS_OPTION -> ViewSongs();
                case VIEW_TOP_SONGS_OPTION -> ViewTopSongs();
                case EXIT_OPTION -> ExitApplication();
                case SEARCH_SONGS_OPTION -> SearchSongs();
                case VIEW_SEARCH_HISTORY_OPTION -> ViewSearchHistory();
                case PLAY_SONG_OPTION -> PlaySong();
            }
        }
    }

    
     /**
     * Get the user menu choice and validate it against the menu options
     * 
     * @return the valid menu choice.
     */  
    private static int GetUserMenuChoice()
    {
        boolean validMenuChoice = false;
        int menuChoice = 0;
        
        while(!validMenuChoice)
        {
            DisplayMenu();
            
            if (userInput.hasNextInt())
            {
                menuChoice = userInput.nextInt();
                userInput.nextLine();
                
                switch (menuChoice)
                {
                    case ADD_SONG_OPTION, REMOVE_SONG_OPTION, VIEW_SONGS_OPTION,
                            VIEW_TOP_SONGS_OPTION, SEARCH_SONGS_OPTION, 
                            VIEW_SEARCH_HISTORY_OPTION, PLAY_SONG_OPTION, 
                            EXIT_OPTION -> validMenuChoice = true;
                            
                    default -> System.out.print("Invalid Choice. Please enter a valid menu option: (0 - 4)\n\n");         
                }
            } 
            
            else
            {
                System.out.println("Invalid Type. Please enter a valid number.\n");
                userInput.nextLine();
            }
        }
        return menuChoice;
    }
    
    
     /**
     * Display the menu to the user
     */
    private static void DisplayMenu()
    { 
        System.out.println("""
                   \tMusic Player\n
                   Choose a menu option:\n
                   \t1. Add Song
                   \t2. Remove Song
                   \t3. View Songs
                   \t4. View Top Songs\n
                   \t5. Search Songs
                   \t6. Search History\n
                   \t7. Music Player
                   \n\t0. Exit Application
                   """);
    }
    
    
     /**
     * Gets user input query for song name and artist name
     * Generates a random playCount from 1 - 1 billion 
     * Generates a random duration from 0.01 - 7 minutes
     * Creates new song object and adds to: songs
     * 
     * Note: Song name and Artist name are not validated as they could technically be called anything
     */   
    private static void AddSong()
    {
        System.out.println("\tAdd Songs\n\nEnter the name of the song you would like to add.");
        String songName = userInput.nextLine();
        
        System.out.println("\nEnter the Artist name for this song.");
        String artistName = userInput.nextLine();
        
        Random rand = new Random();
        int playCount = rand.nextInt(1000000000);
        
        int durationSeconds = rand.nextInt(60);
        int durationMinutes = rand.nextInt(7);
        
        String durationString = (durationMinutes + ":" + durationSeconds);
        
        songs.add(new Song(songName, artistName, durationString, playCount));
        System.out.println(ReturnToMenuStatement());
    }   
    
    
     /**
     * Gets user input for song to remove
     * Initialises an ArrayList to store the songs to remove
     * Iterates through all songs to find a match, if query = match, it will add to ArrayList
     * It will then check if the array is not empty, if it is, remove all items
     * Informs the user what song has been removed
     */   
    private static void RemoveSong()
    {
        ReturnEmptySongStatement();
        
        System.out.println("\tRemove Songs\n\nEnter the name of the song you would like to remove.");
        
        String songToRemove = userInput.nextLine();
        List<Song> songsToRemove = new ArrayList<>(); 
        
        for (Song song : songs)
        {
            if (song.songName.equals(songToRemove))
            {
                songsToRemove.add(song);
            }
        }
        
        if (!songsToRemove.isEmpty())
        { 
            songs.removeAll(songsToRemove);
            System.out.println("\nThe Song " + songToRemove +
                    " has been removed.\n" + ReturnToMenuStatement());
            userInput.nextLine();
        } 
        
        else
        {
            System.out.println("\nThe song " + songToRemove +
                    " could not be found.\n" + ReturnToMenuStatement());
            userInput.nextLine();
        }
    }
    
    
     /**
     * Iterates through songs until all have been outputted
     * Increments a counter so the user knows how many songs are stored
     */   
    private static void ViewSongs()
    {
        ReturnEmptySongStatement();
                
        System.out.println("\tView Songs\n");
        
        int songCounter = 0;
        int songCounterLength = 4;
        
        for (Song song : songs)
        {
            songCounter++;
            String formattedCounter = String.format("%-" + songCounterLength + "s", songCounter);
            System.out.println(formattedCounter + song);
        }
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }
    
    
     /**
     * Initialises ArrayList for storing sorted songs
     * Compares songs against each other with attribute: playCount
     * Creates a collection of the 10 with the highest playCount
     * Once it has reached 10 songs, it will break
     */   
    private static void ViewTopSongs()
    {
        ReturnEmptySongStatement();
        
        System.out.println("\tView Most Played Songs\n");
        
        int topSongCounter = 0;
        int songCounterLength = 3;
        
        List<Song> sortedSongs = new ArrayList<>(songs);
        Collections.sort(sortedSongs, (Song song1, Song song2) 
                -> Integer.compare(song2.playCount, song1.playCount));
        
        for (Song song : sortedSongs)
        {
            topSongCounter++;
            String formattedCounter = String.format("%-" + songCounterLength + "s", + topSongCounter);
            System.out.println(formattedCounter + song);
            
            if (topSongCounter >= 10)
            {
                break;
            }
        }
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }
    
    
    /**
     * Gets user search query, iterates through songs
     * If match with query found then output the match, else ignore
     * If 0 matches are found it will inform the user of so
     * 
     * Note: the query is tested against the song name and artist name
     */    
    private static void SearchSongs()
    {
        ReturnEmptySongStatement();
        
        System.out.println("\tSearch...\n\nPlease enter a search query:\n\n");   
        
        int songCounter = 0;
        int songCounterLength = 4;
        int matchesFound = 0;
        
        String searchQuery = userInput.nextLine();
        searchHistory.add(searchQuery);
        
        for (Song song : songs)
        {
            songCounter++;
            String formattedCounter = String.format("%-" + songCounterLength + "s", songCounter);
            
            if (song.songName.toLowerCase().contains(searchQuery.toLowerCase()) 
                    || song.artistName.toLowerCase().contains(searchQuery.toLowerCase())){
                matchesFound++;
                System.out.println(formattedCounter + song);
            } 
        }
        
        if (matchesFound == 0)
        {
            System.out.println("A song or artist could not be found with that query..");
        }
        
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }
  
    
    /**
     * Iterates through and outputs everything searchHistory ArrayList
     * Validates / informs the user if it is empty
     */
    private static void ViewSearchHistory()
    {
        
        System.out.println("\tView Search History\n\nHistory:\n");
        
        if (searchHistory.isEmpty())
        {
            System.out.println("Your Search History is empty.\n" + ReturnToMenuStatement());
            userInput.nextLine();
            ProcessMenuChoice();        
        }
        
        for (String query : searchHistory)
        {
            System.out.println("  " + query);
        }
        
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }    
    
    
    /**
     * Gets the name of the song to play from user and locates in file location
     * calls PlayAudio passing filePath as a parameter
     */
    private static void PlaySong()
    {
        ReturnEmptySongStatement();
        
        System.out.println("\tMusic Player\n");   
        System.out.println("Enter the name of the song you would like to play\n");   

        String playQuery = userInput.nextLine();
        
        String folderName = "songs";
        String filePath = folderName + "/" + playQuery + ".wav";
        
        PlayAudio(filePath);
    }
    
    
    /**
     * Checks that the file path exists and validates user song choice
     * Plays the selected song from the previous method
     * @param filePath
     */    
    private static void PlayAudio(String filePath) // refactor this method - too big, maybe separate validation from playing the actual audio
    {
        
        try
        {
            File musicPath = new File(filePath);
            
            if(musicPath.exists())
            {
                
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                
                filePath = filePath.replaceFirst("songs/", "");
                filePath = filePath.substring(0, filePath.lastIndexOf('.'));
                System.out.println("Playing " + filePath +
                        "..\n\nPress the Enter key to stop the song and return to menu.");   
                userInput.nextLine();
                
                clip.stop();
            } 
            
            else 
            { 
                System.out.println("This song does not exist. Did you spell it correctly?");
                System.out.println(ReturnToMenuStatement());
                userInput.nextLine();
            }
        }
        
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
        {
            System.out.println(e);
        }
    }
    
    
    private static void ExitApplication()
    {
        System.out.println(EXIT_MESSAGE);
        userInput.close();
        System.exit(0); // exit some other way more graceful
    }    

    
    private static String ReturnToMenuStatement()
    { 
        return RETURN_TO_MENU_STRING;
    }
    
    
    /**
     * Checks if songs is empty, if so inform user and prompt to return to menu
     */ 
    private static void ReturnEmptySongStatement()
    {      
        if (songs.isEmpty())
        {
            System.out.println(EMPTY_SONG_STATEMENT + ReturnToMenuStatement());
            userInput.nextLine();
            ProcessMenuChoice();
        }
    }
}
