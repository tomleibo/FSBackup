package fileVisitors;

import core.Crawler;
import data.Dir;
import interfaces.IFile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public abstract class AbstractFileVisitor extends SimpleFileVisitor<Path> {
    private static final Logger log = Logger.getLogger(AbstractFileVisitor.class.getName());
    public Dir result=null;
    protected Stack<Dir> stack =null;
    Path basePath = null;
    public AbstractFileVisitor(Set<IFile> files) {
        this.stack = new Stack<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        log.log(Level.INFO, "previsiting: " + dir.toString());
        if (basePath==null) {
            basePath=dir;
        }
        Dir idir = new Dir(basePath,dir,attrs);
        if (result==null) {
            result=idir;
        }
        else {
            stack.peek().addDir(idir);
        }
        if (preFilter(dir,attrs)) {
            log.log(Level.INFO,"pushing: "+idir.getRelPath().toString());
            stack.add(idir);
        }
        else {
            return FileVisitResult.SKIP_SUBTREE;
        }
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        log.log(Level.INFO,"postvisiting: " + dir.toString());
        Dir pop = stack.pop();
        log.log(Level.INFO, "popped: " + pop.getRelPath().toString());
        return super.postVisitDirectory(dir, exc);
    }

    public static void main(String args[]) {
        AbstractFileVisitor visitor = new AbstractFileVisitor(null) {

            @Override
            public boolean preFilter(Path file, BasicFileAttributes attrs) {
                return true;
            }

            @Override
            public boolean postFilter(Dir dir) {
                return true;
            }
        };

        Crawler.crawl("c:\\backup", false, 3, visitor);
        Dir dir = visitor.result;
        log.log(Level.INFO, dir.print());

    }

    public abstract boolean preFilter(Path file, BasicFileAttributes attrs);

    public abstract boolean postFilter(Dir dir);

}
