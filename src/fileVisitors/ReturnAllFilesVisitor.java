package fileVisitors;

import data.Fil;
import interfaces.IFile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class ReturnAllFilesVisitor extends AbstractFileVisitor {
    public static ReturnAllFilesVisitor build(Set<IFile> files) {
        return new ReturnAllFilesVisitor(files);
    }

    public ReturnAllFilesVisitor(Set<IFile> files) {
        super(files);
    }

    Set<Fil> files = null;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Fil pfile = new Fil(file,attrs);
        files.add(pfile);
        return super.visitFile(file, attrs);
    }

    @Override
    public boolean filter(Path file, BasicFileAttributes attrs) {
        return true;
    }
}

