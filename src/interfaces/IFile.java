package interfaces;

import data.Dir;
import data.Fil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Created by thinkPAD on 2/2/2016.
 */
public interface IFile {
    public static IFile build(Path path, BasicFileAttributes attrs) throws FileNotFoundException {
        File file = new File(path.toString());
        if (!file.exists()) {
            throw new FileNotFoundException("file not found while building its IFile object.");
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
    Path getPath();
    boolean isDir();
}
