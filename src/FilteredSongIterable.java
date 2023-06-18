/**
 * an interface which allows us to scan a Playlist by a certain filter.
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * foreach where we scan the Playlist by given artist
     * @param artist which an attribute of a song object
     */
    void filterArtist(String artist);

    /**
     * foreach where we scan the Playlist by given genre
     * @param genre which is an enum
     */
    void filterGenre(Song.Genre genre);


    /**
     * foreach where we scan the Playlist by given duration
     * @param dur which is the length of a song (song attribute)
     */
    void filterDuration(int dur);

}