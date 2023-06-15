import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Cloneable, FilteredSongIterator, OrderedSongIterable, Iterable<Song>, Iterator<Song> {

    private List<Song> songList;
    private List<Song> filteredList;
    private String filterArtist;
    private Song.Genre filterG;
    private String filterDur;
    private int index;

    public Playlist() {
        this.songList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.filterArtist = null;
        this.filterG = null;
        this.filterDur = null;
        this.index = 0;
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (this.songList.contains(song))
            throw new SongAlreadyExistsException();
        this.songList.add(song);
    }

    public boolean removeSong(Song song) {
        return this.songList.remove(song);
    }

    @Override
    public Playlist clone() {
        try {
            Playlist clonePlayList = (Playlist) super.clone();
            for (Song song : this.songList)
                clonePlayList.addSong(song.clone());
            return clonePlayList;
        }
        catch (CloneNotSupportedException | SongAlreadyExistsException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object playList) {
        if (!(playList instanceof Playlist))
            return false;
        Playlist checkPlayList = (Playlist) playList;
        for (Song song : checkPlayList)
            if (!(this.songList.contains(song)))
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
    public String toString() {
        StringBuilder playlist = new StringBuilder();
        for (Song song : this.songList) {
            playlist.append(song.toString());
        }
        return "[" + playlist + "]";
    }

    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        this.filterG = genre;
    }

    @Override
    public void filterDuration(int dur) {
        this.filterDur = Song.convertDur(dur);
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        Comparator<Song> comparator = null;
        if (order == ScanningOrder.NAME)
            comparator = Comparator.comparing(Song::getName).thenComparing(Song::getArtist);
        else if (order == ScanningOrder.DURATION)
            comparator = Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist);

        this.filteredList = new ArrayList<>();
        for (Song song : this.songList) {
            this.filteredList.add(song.clone());
        }
        if (comparator != null)
            this.filteredList.sort(comparator);
    }

    @Override
    public Iterator<Song> iterator() {
        prepareFilteredList();
        return this;
    }

    private void prepareFilteredList() {
        if (filterArtist != null)
            setScanningOrder(ScanningOrder.NAME);
        else if (filterG != null)
            setScanningOrder(ScanningOrder.ADDING);
        else if (filterDur != null)
            setScanningOrder(ScanningOrder.DURATION);
        else {
            this.filteredList = new ArrayList<>();
            for (Song song : this.songList) {
                this.filteredList.add(song.clone());
            }
        }
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < this.filteredList.size();
    }

    @Override
    public Song next() {
        return this.filteredList.get(index++);
    }
}
