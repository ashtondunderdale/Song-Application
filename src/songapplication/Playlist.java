/**
 * Song.java
 *
 * This file contains the Playlist class for the song application.
 * Other Files: SongApplication.java, Song.java
 *
 * @author Ashton Dunderdale
 * Date: November 9, 2023
 */

package songapplication;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    List<Song> songs;
    public String name;
    
    public Playlist(List<Song> songs, String name) {
        this.songs = new ArrayList<>(songs);
        this.name = name;
    }
    
    public List<Song> GetSongs(){
        return songs;
    }

    public void AddSong(Song song) {
        songs.add(song);
    }

public void RemoveSongs(List<Song> songsToRemove) {
    songs.removeAll(songsToRemove);
}

}
