public class SongAlreadyExistsException extends RuntimeException{

    /**
     * this exception is thrown if the song is a duplicate to the Playlist
     * that we're trying to add it to.
     */
    public SongAlreadyExistsException() {super("error: song already exists");}

    public SongAlreadyExistsException(String message) {super(message);}

    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
