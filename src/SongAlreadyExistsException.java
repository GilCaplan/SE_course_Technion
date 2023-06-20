public class SongAlreadyExistsException extends RuntimeException{

    /**
     * this exception is thrown if the song is a duplicate to the Playlist
     * that we're trying to add it to.
     */
    public SongAlreadyExistsException() {super("error: song already exists");}

    /**
     * this exception is thrown if the song is a duplicate to the Playlist
     * that we're trying to add it to.
     * @param message is a string that tells us what the error message is
     */
    public SongAlreadyExistsException(String message) {super(message);}

    /**
     * this exception is thrown if the song is a duplicate to the Playlist
     * that we're trying to add it to.
     * @param message is a string that tells us what the error message is
     * @param cause is a throwable object to the cause of the exception
     */
    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
