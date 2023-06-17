import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Iterable<Song>, FilteredSongIterable, OrderedSongIterable{

    private List<Song> songList;
    private List<Song> filteredList;
    private String filterArtist;
    private Song.Genre filterG;
    private int filterDur;


    /**
     * Constructor that creates a new Playlist object with filter options
     * @throws SongAlreadyExistsException if we try to add a duplicate song
     */
    public Playlist() throws SongAlreadyExistsException{
        this.songList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.filterArtist = null;
        this.filterG = null;
        this.filterDur = -1;
    }


    /**
     * Add a song to the Playlist object if the song isn't already in the playlist
     * @param song is a song object that represents a song (name, artist, genre, duration)
     */
    public void addSong(Song song) {
        if (this.songList.contains(song))
            throw new SongAlreadyExistsException();
        this.songList.add(song);
    }

    /**
     * gets the size of the playlist (in particular the songList)
     * @return songList (playlist) size/length
     */
    public int getPlaylistSize(){
        return this.songList.size();
    }

    /**
     * remove a song from out playlist object (list)
     * @param song is a Song object (name, artist, genre, duration)
     * @return true if successfully removed and false if not.
     */
    public boolean removeSong(Song song) {
        return this.songList.remove(song);
    }

    /**
     * Clone this Playlist object and return it as a new playlist object with the same values
     * @return a new Playlist object with the same values of this Playlist object
     */
    @Override
    public Playlist clone() {
        try {
            Playlist clonePlayList = new Playlist();
            for (Song song : this.songList)
                clonePlayList.addSong(song.clone());
            return clonePlayList;
        }
        catch (SongAlreadyExistsException e) {
            return null;
        }
    }

    /**
     * check if two Playlists Objects are the same if they have the same songs
     * and if those songs have the same names & artists
     * (notice that genre & duration can be different)
     * @param playList is a Playlist object that we are comparing to this object
     * @return true if the playlists have the same songs
     */
    @Override
    public boolean equals(Object playList) {
        if (!(playList instanceof Playlist))
            return false;
        Playlist checkPlayList = (Playlist) playList;
        if(this.songList.size() != checkPlayList.getPlaylistSize())
            return false;
        for (Song song : checkPlayList)
            if (!(this.songList.contains(song)))
                return false;
        return true;
    }

    /**
     * an integer number that represents a playlist and any two Playlists that are
     * equal, meaning the this.equals(p2) function returns true therefore, they will
     * share the same hashcode.
     * @return an integer number that represents a playlist
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Song song : filteredList)
            hashCode += song.hashCode();
        return hashCode;
    }

    /**
     * a string representation of a Playlist [(name, artist, genre, duration),...,(...)]
     * @return a string representation of a Playlist
     */
    @Override
    public String toString() {
        if(this.songList.size() == 0)
            return null;
        StringBuilder playlist = new StringBuilder();
        for (Song song : this.songList) {
            playlist.append("(").append(song.toString()).append(")").append(", ");
        }
        return "[" + playlist.substring(0, playlist.length()-2) + "]";
    }

    /**
     * change the filter value for the artist
     * @param artist which an attribute of a song object
     */
    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }

    /**
     * change the filter value for the genre
     * @param genre which is an enum
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        this.filterG = genre;
    }

    /**
     * change the filter value for the duration
     * @param dur which is the length of a song (song attribute)
     */
    @Override
    public void filterDuration(int dur) {
        this.filterDur = dur;
    }

    /**
     * sort the list into a filtered list inside the Playlist object based of the given
     * order which tells us how to scan the list.
     * @param order which is an enum type of how to scan the list
     */
    @Override
    public void setScanningOrder(ScanningOrder order) {
        Comparator<Song> comparator;
        this.filteredList = new ArrayList<>();
        for (Song song : this.songList) {//if add then it's this since it's always in order
            this.filteredList.add(song.clone());
        }//by default the order is by how they are added to the list so no need to do .ADDING
        if (order == ScanningOrder.NAME) {
            comparator = Comparator.comparing(Song::getName).thenComparing(Song::getArtist);
            this.filteredList.sort(comparator);
        }
        if (order == ScanningOrder.DURATION) {
            comparator = Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist);
            this.filteredList.sort(comparator);
        }

    }

    /**
     * an iterator method that filters out the list and iterates through the list
     * @return an iterator object so we can do a foreach loop
     */
    @Override
    public Iterator<Song> iterator() {
        prepareFilteredList();
        return new PlaylistIterator();
    }

    /**
     * a method that filters out the filter list by our filter attributes
     */
    private void prepareFilteredList() {
        if (filterArtist != null)
            this.filteredList.removeIf(s -> !s.getArtist().equals(this.filterArtist));
        if (filterG != null)
            this.filteredList.removeIf(s -> !s.getGenre().equals(this.filterG));
        if (filterDur != -1)
            this.filteredList.removeIf(s -> s.getDuration() > this.filterDur);
        if(filterArtist == null && filterG == null &&  filterDur == -1) {
            this.filteredList = new ArrayList<>();
            for (Song song : this.songList)
                this.filteredList.add(song.clone());
        }
    }

    /**
     * inner iterator class that implements Iterator<Song> in order to iterate
     * through the list.
     */
    private class PlaylistIterator implements Iterator<Song> {
        private int index = 0;

        /**
         * checks if filteredList has a next object
         * @return true if there is a next object to iterate over otherwise false
         */
        @Override
        public boolean hasNext() {
            return index < filteredList.size();
        }

        /**
         * return the next Song and set the index counter up by one for the next
         * check
         * @return the next Song object
         */
        @Override
        public Song next() {
            return filteredList.get(index++);
        }
    }
}
