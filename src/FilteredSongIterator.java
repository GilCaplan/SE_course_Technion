import java.util.Iterator;

/**
 * an interface which allows us to scan a Playlist by a certain filter.
 */
public interface FilteredSongIterator extends Iterator<Song> {


    /**
     * foreach where we scan the Playlist by given artist
     * @param artist which a attribute of a song object
     */
    public void filterArtist(String artist);

    /**
     * foreach where we scan the Playlist by given genre
     * @param genre which is an enum
     */
    public void filterGenre(Song.Genre genre);


    /**
     * foreach where we scan the Playlist by given duration
     * @param dur which is the length of a song (song attribute)
     */
    public void filterDuration(String dur);

}