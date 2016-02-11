package fileVisitors;

import core.Crawler;
import data.Dir;
import data.Fil;
import interfaces.IFile;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.Stack;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public abstract class AbstractFileVisitor extends SimpleFileVisitor<Path> implements IFileVisitor {

    public Dir result=null;
    protected Stack<Dir> stack =null;

    public AbstractFileVisitor(Set<IFile> files) {
        this.stack = new Stack<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("previsiting: "+dir.toString());
        Dir idir = (Dir)IFile.build(dir, attrs);
        if (result==null) {
            result=idir;
        }
        else {
            stack.peek().addFile(idir);
        }
        stack.add(idir);
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("filevisiting: "+file.toString());
        IFile ifile = IFile.build(file, attrs);
        stack.peek().addFile(ifile);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("postvisiting: " + dir.toString());
        stack.pop();
        return super.postVisitDirectory(dir, exc);
    }

    public static void main(String args[]) {
        AbstractFileVisitor visitor = new AbstractFileVisitor(null) {
            @Override
            public boolean filter(Path file, BasicFileAttributes attrs) {
                return true;
            }
        };
        Crawler.crawl("c:\\backup",false,2,visitor);
        Dir dir = visitor.result;
        System.out.println(dir.toString());

    }

    public abstract boolean filter(Path file, BasicFileAttributes attrs);

}
