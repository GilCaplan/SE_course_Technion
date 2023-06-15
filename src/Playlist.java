import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Iterable<Song>, FilteredSongIterable, OrderedSongIterable {

    private List<Song> songList;
    private List<Song> filteredList;
    private String filterArtist;
    private Song.Genre filterG;
    private int filterDur;

    public Playlist() {
        this.songList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.filterArtist = null;
        this.filterG = null;
        this.filterDur = -1;
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (this.songList.contains(song))
            throw new SongAlreadyExistsException();
        this.songList.add(song);
    }

    public int getPlaylistSize(){
        return this.songList.size();
    }

    public boolean removeSong(Song song) {
        return this.songList.remove(song);
    }

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

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Song song : filteredList) {
            hashCode += song.hashCode();
        }
        return hashCode;
    }

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
        this.filterDur = dur;
    }

    @Override
    public void setScanningOrder(ScanningOrder order) {
        Comparator<Song> comparator;
        this.filteredList = new ArrayList<>();
        for (Song song : this.songList) {//if add then it's this since it's always in order
            this.filteredList.add(song.clone());
        }
        if (order == ScanningOrder.NAME) {
            comparator = Comparator.comparing(Song::getName).thenComparing(Song::getArtist);
            this.filteredList.sort(comparator);
        }
        if (order == ScanningOrder.DURATION) {
            comparator = Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist);
            this.filteredList.sort(comparator);
        }

    }

    @Override
    public Iterator<Song> iterator() {
        prepareFilteredList();
        return new PlaylistIterator();
    }


    private void prepareFilteredList() {
        if (filterArtist != null)
            this.filteredList.removeIf(s -> !s.getArtist().equals(this.filterArtist));
        if (filterG != null)
            this.filteredList.removeIf(s -> !s.getGenre().equals(this.filterG));
        if (filterDur != -1)
            this.filteredList.removeIf(s -> s.getDuration() > this.filterDur);
        if(filterArtist == null && filterG == null &&  filterDur == -1) {
            this.filteredList = new ArrayList<>();
            for (Song song : this.songList) {
                this.filteredList.add(song.clone());
            }
        }
    }

    private class PlaylistIterator implements Iterator<Song> {
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < filteredList.size();
        }

        @Override
        public Song next() {
            return filteredList.get(index++);
        }
    }
}
