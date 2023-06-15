public class Song {
    private final String name;

    private final String artist;

    private final Genre genre;
    private final String duration;

    /**
     * Constructor that makes a new song object by its fields
     * @param name of song
     * @param artist name
     * @param genre which is the type of song
     * @param duration of song
     */
    public Song(String name, String artist, Genre genre, String duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        String[] dur = duration.split(":");
        if(dur[1].length() == 0)
            dur[1] = "00";
        else if(dur[1].length() == 1)
            dur[1] = "0" + dur[1];
        this.duration = dur[0] + dur[1];
    }

    /**
     * make a deep copy of the song object
     * @return a deep copy of the song object
     */
    @Override
    public Song clone(){
        try {
            return (Song) super.clone();
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }

    /**
     * compare this song to given song
     * @param song is a song
     * @return true if the songs have the same name and artist
     */
    @Override
    public boolean equals(Object song){
        if(!(song instanceof Song))
            return false;
        Song checkSong = (Song) song;
        return this.name.equals(checkSong.name) && this.artist.equals(checkSong.artist);
    }

    /**
     * make a string of the songs attributes (name, artist, genre, duration)
     * @return (name, artist, genre, duration)
     */
    @Override
    public String toString(){
        return "("+ this.name + ", " + this.artist + ", " + this.genre + ", " +this.duration+")";
    }

    @Override
    public int hashCode() {
        int result = duration.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }



    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO

    }
}
