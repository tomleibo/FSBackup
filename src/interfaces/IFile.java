package interfaces;

import java.nio.file.Path;

/**
 * Created by thinkPAD on 2/2/2016.
 */
public interface IFile {
    boolean isSameFile(IFile other);
    boolean isChanged(IFile otherFile);
    String getModificationDateString();
    Path getRelPath();
    boolean isDir();
}
