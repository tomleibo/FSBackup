package fileVisitors;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public abstract class AbstractFileVisitor extends SimpleFileVisitor<Path> implements IFileVisitor {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("previsiting: "+dir.toString());
        return FileVisitResult.CONTINUE;
    }

}
