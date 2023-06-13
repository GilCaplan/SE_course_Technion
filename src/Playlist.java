import java.util.ArrayList;
import java.util.List;

public class Playlist implements Cloneable , FilteredSongIterable {
    private List<Song> songList= new ArrayList<>();

    public void addSong(Song song){
        if(!(this.songList.contains(song)))
            this.songList.add(song);
        else
            throw new SongAlreadyExistsException();
    }

    public boolean removeSong(Song song){
        if (this.songList.contains(song)){
            this.songList.remove(song);
            return true;
        }
        else
            return false;
    }
    @Override
    public Playlist clone() {
        try{
            Playlist clonePlayList= (Playlist) super.clone();
            for(Song song:this.songList){
                clonePlayList.addSong(song.clone());
            }
            return clonePlayList.clone();
        }
        catch (CloneNotSupportedException e){
            return  null;
        }
    }
    @Override
    public boolean equals(Object playList){
        if(!(playList instanceof Playlist))
            return false;
        Playlist newPlayList= (Playlist) playList;
        for(Song song:this.songList){
            if(!(newPlayList.songList.contains(song)))
                return false;
        }
        return true;
    }

    @Override
    public String toString(){
        String list="[";
        for(Song song:this.songList){
            list+=song.toString();
        }
        list+="]";
        return list;
    }



}
