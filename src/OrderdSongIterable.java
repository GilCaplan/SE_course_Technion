import java.util.Iterator;

/**
 * an Interface in order to set the order of a playlist
 */
public interface OrderdSongIterable extends Iterator<Song> {

    /**
     * Method to set the order in which we scan a playlist
     * @param order which is an enum type of how to scan the list
     */
    public void setScanningOrder(ScanningOrder order);
}
