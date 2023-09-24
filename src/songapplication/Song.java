/**
 * Song.java
 *
 * This file contains the Song class for the song application.
 *
 * @author Ashton Dunderdale
 * Date: September 24, 2023
 */

package songapplication;

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