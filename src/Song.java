public class Song {
    private final String name;

    private final String artist;

    private Genre genre;
    private int duration;

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }
    @Override
    public Song clone(){
        try {
            Song cloneSong= (Song) super.clone();
            return cloneSong;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
    @Override
    public boolean equals(Object song){
        if(!(song instanceof Song))
            return false;
        Song newSong=(Song) song;
        return this.name == newSong.name&& this.artist==newSong.artist;
    }

    @Override
    public String toString(){
        return "("+ this.name + "," + this.artist+ "," + this.genre+"," +this.duration+")";
    }



}
