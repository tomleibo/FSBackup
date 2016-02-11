package fileVisitors;

import data.Fil;
import interfaces.IFile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public abstract class AbstractFileVisitor extends SimpleFileVisitor<Path> implements IFileVisitor {
    //TODO figure how to build the file system tree in one IFile root recursively via the file visitor.

    Set<IFile> files = null;
    Set<IFile> currentDir =null;

    public AbstractFileVisitor(Set<IFile> files) {
        this.files = files;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        IFile fsFile = IFile.build(file, attrs);
        files.add(fsFile);
        return super.visitFile(file, attrs);
    }

    public abstract boolean filter(Path file, BasicFileAttributes attrs);

}
