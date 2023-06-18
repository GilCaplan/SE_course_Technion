/**
 * an Interface in order to set the order of a playlist
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Method to set the order in which we scan a playlist
     * @param order which is an enum type of how to scan the list
     */
    void setScanningOrder(ScanningOrder order);
}
