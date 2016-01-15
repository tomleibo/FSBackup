package fileVisitors;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class ReturnIsChangedVisitor extends AbstractFileVisitor   {

    @Override
    public boolean filter(Path file, BasicFileAttributes attrs) {
        return false;
    }
}
