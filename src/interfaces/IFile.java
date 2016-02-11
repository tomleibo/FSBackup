package interfaces;

import data.Dir;
import data.Fil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Created by thinkPAD on 2/2/2016.
 */
public interface IFile {
    static IFile build(Path path, BasicFileAttributes attrs) {
        File file = new File(path.toString());
        if (!file.exists()) {
            return null;
        }
        if (file.isDirectory()) {
            return new Dir(path,attrs);
        }
        else {
            return new Fil(path,attrs);
        }
    }
    boolean isSameFile(IFile other);
    boolean isChanged(IFile otherFile);
    String getModificationDateString();
    byte[] getHash();
    Path getPath();
    boolean isDir();
}
