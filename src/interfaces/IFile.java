package interfaces;

import java.util.Date;

/**
 * Created by thinkPAD on 2/2/2016.
 */
public interface IFile {
    boolean isChanged(IFile otherFile);
    String getModificationDateString();
    byte[] getHash();
}
