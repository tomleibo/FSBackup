package fileVisitors;

import data.Dir;
import interfaces.IFile;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class ReturnAllFilesVisitor extends AbstractFileVisitor {
    public ReturnAllFilesVisitor(Set<IFile> files) {
        super(files);
    }

    @Override
    public boolean preFilter(Path file, BasicFileAttributes attrs) {
        return true;
    }

    @Override
    public boolean postFilter(Dir dir) {
        return true;
    }


}

