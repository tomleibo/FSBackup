package core;

import data.Fil;
import fileVisitors.IFileVisitor;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class Crawler {

    public static void crawl(String path,boolean followSymbolic, int maxDepth, IFileVisitor visitor) {
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
