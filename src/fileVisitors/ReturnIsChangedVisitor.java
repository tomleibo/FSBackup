package fileVisitors;

import data.Fil;
import interfaces.IFile;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class ReturnIsChangedVisitor extends AbstractFileVisitor {
    public ReturnIsChangedVisitor(Set<IFile> files) {
        super(files);
    }

    //TODO implement

    @Override
    public boolean filter(Path file, BasicFileAttributes attrs) {
        return false;
    }
}

