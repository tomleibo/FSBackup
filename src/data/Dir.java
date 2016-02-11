package data;

import interfaces.IFile;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class Dir implements IFile {
    //TODO: building hashset with initial capacity will increase performance.

    Path path;
    Set<IFile> files;
    boolean isLazy;

    public Dir(Path path, BasicFileAttributes attrs) {
        this.path=path;
        this.isLazy=true;
        this.files = new HashSet<>();
    }

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
        return false;
    }

    @Override
    public boolean isChanged(IFile otherFile) {
        return false;
    }

    @Override
    public String getModificationDateString() {
        return null;
    }

    @Override
    public byte[] getHash() {
        return new byte[0];
    }

    @Override
    public Path getPath() {
        return null;
    }

    @Override
    public boolean isDir() {
        return true;
    }
}
