package core;

import fileVisitors.AbstractFileVisitor;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class Crawler {

    public static void crawl(String path,boolean followSymbolic, int maxDepth, AbstractFileVisitor visitor) {
        Path p = Paths.get(path);
        try {
            Files.walkFileTree(p,
                    followSymbolic ? EnumSet.of(FileVisitOption.FOLLOW_LINKS) : EnumSet.noneOf(FileVisitOption.class),
                    maxDepth>0?maxDepth:Integer.MAX_VALUE,
                    visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
