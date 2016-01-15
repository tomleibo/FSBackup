package fileVisitors;

import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public interface IFileVisitor extends FileVisitor<Path>{
    public boolean filter(Path file, BasicFileAttributes attrs);

}
