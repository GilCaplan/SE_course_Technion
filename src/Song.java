public class Song implements Cloneable {

    private final String name;

    private final String artist;

    private final Genre genre;
    private int duration;

    /**
     * Constructor that makes a new song object by its fields
     * @param name of song
     * @param artist name
     * @param genre which is the type of song
     * @param duration of song
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * make a deep copy of the song object
     * @return a deep copy of the song object, null if it's not possible
     */
    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
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
        //songs are defined to be equal if and only if they have the name and artist.
        return this.name.equals(checkSong.name) && this.artist.equals(checkSong.artist);
    }

    /**
     * make a string of the songs attributes (name, artist, genre, duration)
     * @return (name, artist, genre, duration)
     */
    @Override
    public String toString(){
        return this.name + ", " + this.artist + ", " + this.genre + ", " +strDur(this.duration);
    }

    /**
     * an integer number that is assigned to a Song object
     * if two Song objects are equal (meaning s1.equals(s2) is true) then
     * hashCode will give them the same integer representation
     * @return an integer number that represents a Song object
     */
    @Override
    public int hashCode() {
        int result = 0;
        result = artist.hashCode()+ 67 * result;
        result = name.hashCode() + 173 * result;
        return result;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public Genre getGenre() {
        return genre;
    }

    /**
     * takes an integer, typically that represents the duration attribute
     * and turns it into a string representation -> mm:ss
     * @param newDur is an int that represents a time duration
     * @return string representation of the integer newDur (duration)
     */
    public static String strDur(int newDur){
        String[] dur = ((newDur/60)+":"+(newDur%60)).split(":");
        if(dur[1].length() == 0)
            dur[1] = "00";
        else if(dur[1].length() == 1)
            dur[1] = "0" + dur[1];
        return dur[0] + ":" + dur[1];
    }

    public void setDuration(int newDur) {
        this.duration = newDur;
    }

    /**
     * Enum that represents the Genre of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO;

        /**
         * Returns the name of enum constant, as
         * declared.
         * @return the name of this enum constant
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }
}
