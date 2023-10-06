/**
 * Song.java
 *
 * This file contains the Song class for the song application.
 * Other Files: SongApplication.java
 *
 * @author Ashton Dunderdale
 * Date: September 24, 2023
 */


package songapplication;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * Represents a song with the associated attributes
 */
public class Song {
    public String songName;
    public String artistName;
    public String durationString;
    public int playCount;
    
    
    /**
     * Creates a new song object with the associated attributes
     * 
     * @param songName          The name of the song.
     * @param artistName        The name of the artist.
     * @param durationString    A string representation of the song duration.
     * @param playCount         The number of times the song has been played.
     */
    public Song(String songName, String artistName, String durationString, int playCount){
        this.songName = songName;
        this.artistName = artistName;
        this.durationString = durationString;
        this.playCount = playCount;
    }
    
    
    /**
     * Formats the output of the songs to make it cleaner and more readable
     * @return the formatted attributes of the objects
     */
    @Override
    public String toString(){
        int songNameWidth = 30;
        int artistNameWidth = 30;
        int durationWidth = 20;
        int playCountWidth = 15;
        
        String formattedSongName = String.format("%-" + songNameWidth + "s", songName);
        String formattedArtistName = String.format("%-" + artistNameWidth + "s", artistName);
        String formattedDuration = String.format("%-" + durationWidth + "s", "Duration: " + durationString);
        
        String formattedPlayCount = NumberFormat.getNumberInstance(Locale.US).format(playCount);
        formattedPlayCount = String.format("%-" + playCountWidth + "s", "Play Count: " + formattedPlayCount);
        
        return formattedSongName + formattedArtistName + formattedDuration + formattedPlayCount;
    }
}