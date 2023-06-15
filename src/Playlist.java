import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Cloneable {

    //list that contains all Songs in our Playlist
    private List<Song> songList;


    /**
     * Constructor to create a new Playlist - a new ArrayList of songs.
     */
    public Playlist() {
        this.songList = new ArrayList<>();
    }

    /**
     * Method to add a song to our Playlist if it isn't already there
     * @param song to add
     * @throws SongAlreadyExistsException if song already is in our Playlist
     */
    public void addSong(Song song) throws SongAlreadyExistsException {
        if(!this.songList.contains(song))
            this.songList.add(song);
        else
            throw new SongAlreadyExistsException();
    }


    /**
     * remove a song from the Playlist if it exists there
     * @param song that we want to remove
     * @return true if we successfully removed the song otherwise false.
     */
    public boolean removeSong(Song song){
        if (this.songList.contains(song)){
            this.songList.remove(song);
            return true;
        }
        return false;
    }

    /**
     * Make a deep copy of our Playlist object
     * @return deep copy of the Playlist object
     */
    @Override
    public Playlist clone() {
        try{
            Playlist clonePlayList= (Playlist) super.clone();
            for(Song song : this.songList)
                clonePlayList.addSong(song.clone());
            return clonePlayList;
        }
        catch (CloneNotSupportedException | SongAlreadyExistsException e){
            return  null;
        }
    }
    @Override
    public boolean equals(Object playList){
        if(!(playList instanceof Playlist))
            return false;
        Playlist checkPlayList = (Playlist) playList;
        for(Song song : checkPlayList)
            if(!(this.songList.contains(song)))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        for (Song song : songList) {
            hashCode = 31 * hashCode + song.hashCode();
        }
        return hashCode;
    }

    @Override
    public String toString(){
        StringBuilder playlist = new StringBuilder();
        for(Song song : this.songList){
            playlist.append(song.toString());
        }
        return "[" + playlist + "]";
    }

    public class PlaylistIterator implements Iterator<Song>{

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return false;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Song next() {
            return null;
        }
    }
}
