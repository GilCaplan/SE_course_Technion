public class SongAlreadyExistsException extends RuntimeException{
    public SongAlreadyExistsException() {super("error: song already existing");}

    public SongAlreadyExistsException(String message) {super(message);}

    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
