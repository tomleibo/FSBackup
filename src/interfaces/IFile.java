package interfaces;

import java.nio.file.Path;
import java.util.Date;

/**
 * Created by thinkPAD on 2/2/2016.
 */
public interface IFile {
    boolean isSameFile(IFile other);
    boolean isChanged(IFile otherFile);
    String getModificationDateString();
    byte[] getHash();
    Path getPath();
}
