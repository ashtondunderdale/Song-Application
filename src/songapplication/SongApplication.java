/**
 * SongApplication.java
 * 
 * This file contains the main class for the song application
 * Other Files: Song.Java, Playlist.java
 * 
 * @author Ashton Dunderdale
 * Date: October 17, 2023 
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
 * It also defines constants for repeatedly used
 * messages for the recursive message methods
 * 
 * initialises Scanner class and ArrayList to store song objects
 * 
 */
public class SongApplication 
{  
    // ArrayList to store song objects
    private static final List<String> searchHistory = new ArrayList<>();
    
    private static final List<Song> songs = new ArrayList<>();   
    private static final List<Playlist> playlists = new ArrayList<>();
   
    /* constants for menu options and message methods, removes unessecary, 
     * unwanted "magic numbers"
    */
    private static final int EXIT_OPTION = 0;
    private static final int ADD_SONG_OPTION = 1;
    private static final int REMOVE_SONG_OPTION = 2;
    private static final int ADD_PLAYLIST_OPTION = 3;
    private static final int REMOVE_PLAYLIST_OPTION = 4;
    private static final int VIEW_SONGS_OPTION = 5;
    private static final int VIEW_TOP_SONGS_OPTION = 6;
    private static final int SEARCH_SONGS_OPTION = 7;
    private static final int VIEW_SEARCH_HISTORY_OPTION = 8;
    private static final int MUSIC_PLAYER_OPTION = 9;
    private static final int PLAY_RANDOM_SONG_OPTION = 10;
    
    
    private static final String RETURN_TO_MENU_STRING = 
            "\nPress the enter key to return to the menu";
    private static final String EXIT_MESSAGE = 
        """      
        Thank you for using the song application. The Program will now close.
        """;
    
    private static final String EMPTY_SONG_STATEMENT = 
        "\nThere are no songs stored, add songs first to use other features.\n";

    static Scanner userInput = new Scanner(System.in);
    static Random rand = new Random();

    
    /**
     * The entry point of the program. Initialises the list of songs,
     * displays menu, calls initial starter method..
     * 
     * @param args (not used).
     */
    public static void main(String[] args)
    { 
        songs.add(new Song
        ("Let It Happen", "Tame Impala", "4:16", 108998497));
        songs.add(new Song
        ("Bad Romance", "Lady Gaga", "3:34", 1210515591)); 
        songs.add(new Song
        ("Summertime Sadness", "Lana Del Ray", "4:26", 687585745));
        songs.add(new Song
        ("The Less I Know The Better", "Tame Impala", "3:37", 557449061));
        songs.add(new Song
        ("Smells Like Teen Spirit", "Nirvana", "5:55", 758473934));
        songs.add(new Song
        ("Poker Face", "Lady Gaga", "3:38", 123955524)); // this is the only one that has an audio file
        songs.add(new Song
        ("Tribute", "Tenacious D", "4:52", 98746375));
        songs.add(new Song
        ("Be Quiet And Drive", "Deftones", "8:02", 429483942));
        songs.add(new Song
        ("Everlong", "Foo Fighters", "3:35", 77642142));
        songs.add(new Song
        ("Change", "Deftones", "4:31", 223454389));
               
        Playlist initialPlaylist = new Playlist(songs, "initialPlaylist");
        playlists.add(initialPlaylist);
        
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
                case ADD_PLAYLIST_OPTION -> AddPlaylist();
                case REMOVE_PLAYLIST_OPTION -> RemovePlaylist();
                case VIEW_SONGS_OPTION -> ViewSongs();
                case VIEW_TOP_SONGS_OPTION -> ViewTopSongs();             
                case SEARCH_SONGS_OPTION -> SearchSongs();
                case VIEW_SEARCH_HISTORY_OPTION -> ViewSearchHistory();
                case MUSIC_PLAYER_OPTION -> SelectSong();
                case PLAY_RANDOM_SONG_OPTION -> playRandomSong();               
                case EXIT_OPTION -> ExitApplication();
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
                    case ADD_SONG_OPTION, 
                            REMOVE_SONG_OPTION, 
                            ADD_PLAYLIST_OPTION,
                            REMOVE_PLAYLIST_OPTION,
                            VIEW_SONGS_OPTION,
                            VIEW_TOP_SONGS_OPTION, 
                            SEARCH_SONGS_OPTION, 
                            VIEW_SEARCH_HISTORY_OPTION, 
                            MUSIC_PLAYER_OPTION,
                            PLAY_RANDOM_SONG_OPTION, 
                            EXIT_OPTION -> validMenuChoice = true;
                            
                    default -> System.out.print("""
                        Invalid Choice. Enter a valid menu option: (0 - 8)
                                                """);         
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
                   Select a Menu Option by Entering a Number: 
                   \t___________________________________________
                   \t1  | Add Song           2  | Remove Song
                   \t3  | Add Playlist       4  | Remove Playlist
                   \t5  | View Songs         6  | View Top Songs\n
                   \t7  | Search Songs       8  | Search History\n
                   \t9  | Music Player       10 | Play Random Song
                   
                   \t0 | Exit Application |
                   """);
    }
    
    
     /**
     * Gets user input query for song name and artist name
     * Generates a random playCount from 1 - 1 billion 
     * Generates a random duration from 0.01 - 7 minutes
     * Creates new song object and adds to: songs
     * 
     * Note: Song name and Artist name are not validated 
     * as they could technically be called anything
     */   
    private static void AddSong()
    {
        System.out.println("""
            \tAdd Songs
            Enter the name of the song you would like to add.""");
        String songName = userInput.nextLine(); // add a length limit
        
        System.out.println("\nEnter the Artist name for this song.");
        String artistName = userInput.nextLine(); // add a length limit
        
        int playCount = rand.nextInt(1000000000);       
        int durationSeconds = rand.nextInt(10, 60);
        int durationMinutes = rand.nextInt(7);
        
        String durationString = (durationMinutes + ":" + durationSeconds);
        
        Playlist selectedPlaylist = ChoosePlaylist();
       
        selectedPlaylist.AddSong(new Song(songName, artistName, durationString, playCount));
        System.out.println("\n" + songName + " by " + artistName + 
                " has been added to " + selectedPlaylist.name);
        
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }   
    
    
     /**
     * Gets user input for song to remove
     * Initialises an ArrayList to store the songs to remove
     * 
     * Iterates through all songs to find a match, if query = match, 
     * it will add to ArrayList
     * 
     * It will then check if the array is not empty, if it is, remove all items
     * Informs the user what song has been removed
     */   
    private static void RemoveSong()
    {
        ReturnEmptySongStatement();
        
        System.out.println("""
            \tRemove Songs
            Enter the name of the song you would like to remove.""");
        
        String songToRemove = userInput.nextLine();
        
        Playlist selectedPlaylist = ChoosePlaylist();
        
        List<Song> songsToRemove = new ArrayList<>();         
        for (Song song : selectedPlaylist.GetSongs())
        {
            if (song.songName.equals(songToRemove))
            {
                songsToRemove.add(song);
            }
        }
        
        if (!songsToRemove.isEmpty())
        { 
            selectedPlaylist.RemoveSongs(songsToRemove);
            System.out.println("\nThe Song " + songToRemove +
                    " has been removed from" + 
                    selectedPlaylist.name + "\n" + ReturnToMenuStatement());
            userInput.nextLine();
        } 
        
        else
        {
            System.out.println("\nThe song " + songToRemove +
                    " could not be found in the playlist" + 
                    selectedPlaylist.name + "\n" + ReturnToMenuStatement());
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
        
        if(playlists.isEmpty())
        {
            System.out.println("No playlists available.");
        } 
        else
        {
            System.out.println("Choose a playlist to view songs\n");

            for (int i = 0; i < playlists.size(); i++) 
            {
                System.out.println((i + 1) + " | " + playlists.get(i).name);
            }
            int playlistChoice = userInput.nextInt();
            userInput.nextLine();

            if(playlistChoice >= 1 && playlistChoice <= playlists.size())
            {
                Playlist selectedPlaylist = playlists.get(playlistChoice - 1);

            int songCounter = 0;
            int songCounterLength = 4;
            
            System.out.println("\n");

            for (Song song : selectedPlaylist.GetSongs())
            {
                songCounter++;
                String formattedCounter = String.format("%-" + 
                    songCounterLength + "s", songCounter);
                System.out.println(formattedCounter + song);
            }
            } 
            else
            {
                System.out.println("Invalid playlist choice.");
            }
        }
        System.out.println(ReturnToMenuStatement());
        userInput.nextLine();
    }
    
    /**
    * Asks the user to choose a playlist from available playlists
    * @return The selected playlist
    */
    private static Playlist ChoosePlaylist() {
        System.out.println("\nChoose a playlist:");

        for (int i = 0; i < playlists.size(); i++) 
        {
            System.out.println((i + 1) + " | " + playlists.get(i).name);
        }

        int playlistChoice;

        try 
        {
            playlistChoice = userInput.nextInt();
            userInput.nextLine();
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.out.println("Invalid input. Defaulted to first playlist.");
            userInput.nextLine(); // Clear the invalid input
            return GetDefaultPlaylist();
        }

        if (playlistChoice >= 1 && playlistChoice <= playlists.size()) 
        {
            return playlists.get(playlistChoice - 1);
        } 
        else 
        {
            System.out.println("Invalid playlist choice. Defaulted to first playlist.");
            return GetDefaultPlaylist();
        }
    }
    
    /**
     * Checks if playlist is empty, creates new playlist if non existent, else just uses default
     * @return Default Playlist / new default playlist
     */
    private static Playlist GetDefaultPlaylist() 
    {
    if (!playlists.isEmpty()) 
    {
        return playlists.get(0);
    } 
    else 
    {
        System.out.println("No playlists available. Creating a new one.");
        String defaultPlaylistName = "Default Playlist";
        Playlist defaultPlaylist = new Playlist(new ArrayList<>(), defaultPlaylistName);
        playlists.add(defaultPlaylist);
        return defaultPlaylist;
    }
}
    
    /**
    * Adds a new playlist to the list of playlists.
    */
    private static void AddPlaylist() 
    {
        System.out.println("\tAdd Playlist\nEnter the name of the playlist:");
        String playlistName = userInput.nextLine();

        playlists.add(new Playlist(new ArrayList<>(), playlistName));

        System.out.println("Playlist '" + playlistName + 
                "' has been added.\n" + ReturnToMenuStatement());
        userInput.nextLine();
    }
    
    /**
    * Lists available playlists
    * Removes a playlist from the list of playlists from user input.
    */
   private static void RemovePlaylist() 
   {
       System.out.println("\tRemove Playlist\nChoose a playlist to remove:");

       for (int i = 0; i < playlists.size(); i++) 
       {
           System.out.println((i + 1) + " | " + playlists.get(i).name);
       }

       int playlistChoice = userInput.nextInt();
       userInput.nextLine();

       if (playlistChoice >= 1 && playlistChoice <= playlists.size()) 
       {
           Playlist playlistToRemove = playlists.remove(playlistChoice - 1);
           System.out.println("Playlist '" + playlistToRemove.name +
                   "' has been removed.\n" + ReturnToMenuStatement());
           userInput.nextLine();
       } 
       else 
       {
           System.out.println("Invalid playlist choice.\n" + ReturnToMenuStatement());
           userInput.nextLine();
       }
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
        
        List<Song> allSongs = new ArrayList<>();
        
        for (Playlist playlist : playlists) {
            allSongs.addAll(playlist.GetSongs());
        }
        
        Collections.sort(allSongs, (Song song1, Song song2) 
            -> Integer.compare(song2.playCount, song1.playCount));
        
        for (Song song : allSongs)
        {
            topSongCounter++;
            String formattedCounter = String.format("%-" + 
                    songCounterLength + "s", + topSongCounter);
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
        
        Playlist selectedPlaylist = ChoosePlaylist();

        for (Song song : selectedPlaylist.GetSongs())
        {
            songCounter++;
            String formattedCounter = String.format("%-" + 
                    songCounterLength + "s", songCounter);
            
            if (song.songName.toLowerCase().contains(searchQuery.toLowerCase()) 
                    || song.artistName.toLowerCase().contains(searchQuery.toLowerCase()))
            {
                matchesFound++;
                System.out.println(formattedCounter + song);
            } 
        }
        
        if (matchesFound == 0)
        {
            System.out.println(""
                + "A song or artist could not be found with that query..");
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
            System.out.println("Your Search History is empty.\n" 
                    + ReturnToMenuStatement());
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
     * Gets the name of the song to play from user and 
     * creates a File object to pass as a parameter
     * 
     * calls PlayAudio passing filePath as a parameter
     */
    private static void SelectSong()
    {
        ReturnEmptySongStatement();
        
        System.out.println("\tMusic Player\n");   
        System.out.println("Enter the name of the song you would like to play\n");   

        String playQuery = userInput.nextLine();
        
        String folderName = "songs";
        String filePath = folderName + "/" + playQuery + ".wav";
        
        GetSong(filePath);
    }
    
    
    /**
     * Checks that the file path exists inside a try / except and 
     * validates user song choice
     * 
     * Handles the relevant exceptions, unsupported file type, etc
     * 
     * If all valid it will call playSong with the parameters below
     * @param filePath
     */    
    private static void GetSong(String filePath)
    {
        try
        {
            File musicPath = new File(filePath);
            
            if(musicPath.exists())
            {
                playSong(musicPath, filePath);
            } 
            
            else 
            { 
                System.out.println("This song does not exist. "
                    + "Did you spell it correctly?");
                System.out.println(ReturnToMenuStatement());
                userInput.nextLine();
            }
        }
        
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            System.out.println(e);
        }
    }
    
    
    /**
     * Plays the selected song from the previous method
     * Prompts the user to press enter when they would like to stop the song
     * 
     * Throws some common exceptions that may be thrown 
     * when dealing with file types, etc
     * 
     * @param musicPath
     * @param filePath
     */    
    private static void playSong(File musicPath, String filePath) 
        throws UnsupportedAudioFileException, IOException, LineUnavailableException
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
    
   
     /**
     * Generates a random index of the song objects in songs and finds that song
     * with the index generated, gets song name
     * 
     * Creates folder path, calls GetSong with the file pat variable
     */
    private static void playRandomSong() {
        System.out.println("\tMusic Player\n");

        Playlist selectedPlaylist = ChoosePlaylist();

        if (selectedPlaylist.GetSongs().isEmpty()) 
        {
            System.out.println("No songs available in the selected playlist. "
                    + "Returning to the menu.");
            userInput.nextLine();
            return;
        }

        int randomSongIndex = (int) (Math.random() * selectedPlaylist.GetSongs().size());

        String randomSong = selectedPlaylist.GetSongs().get(randomSongIndex).songName;
        randomSong = randomSong.toLowerCase();

        String folderName = "songs";
        String filePath = folderName + "/" + randomSong + ".wav";

        GetSong(filePath);
    }

    
    
    private static void ExitApplication()
    {
        System.out.println(EXIT_MESSAGE);
        userInput.close();
        System.exit(0);
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
