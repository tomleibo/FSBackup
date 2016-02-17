package data;

import interfaces.IFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class Dir implements IFile {
    //TODO: building hashset with initial capacity will increase performance.


    Path path;
    Map<Dir,Dir> dirs;
    boolean isLazy;

    public Dir(Path path, BasicFileAttributes attrs) {
        this.path=path;
        this.isLazy=true;
        this.dirs = new HashMap<>();
    }

    public boolean addDir(Dir dir) {return dirs.put(dir,dir)==null;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dir dir = (Dir) o;

        return getPath().equals(dir.getPath());

    }

    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

    @Override
    public boolean isSameFile(IFile other) {
        return path.equals(other.getPath()) && (this.isDir() == other.isDir());
    }

    public Map<Dir,Dir> getDirs() {
        return dirs;
    }

    public Collection<File> getFiles(){
        List<File> files = Arrays.asList(new File(path.toString()).listFiles());
        return files.stream().filter((file)->file.isFile()).collect(Collectors.toList());
    }


    @Override
    public boolean isChanged(IFile otherFile) {
        throw new RuntimeException("unsupported exception");
    }

    @Override
    public String getModificationDateString() {
        return null;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public boolean isDir() {
        return true;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setIsLazy(boolean isLazy) {
        this.isLazy = isLazy;
    }


    public String print() {
        StringBuilder sb= new StringBuilder();
        sb.append("Dir: "+path.toString()+"\n");
        for (Dir dir : dirs.keySet()){
            String[] lines = dir.print().split("\n");
            for (String line:lines) {
                sb.append("\t"+line+"\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Dir{" +
                "path=" + path +
                '}';
    }
}
