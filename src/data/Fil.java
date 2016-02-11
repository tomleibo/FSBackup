package data;

import interfaces.IFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class Fil implements IFile{
    protected Path path;
    protected String fileName;
    protected String parentDirectoryPath;
    protected long size;
    protected long creationTime;
    protected long modificationTime;
    protected Boolean isSymbolic;

    public Fil(Path path, BasicFileAttributes attrs) {
        this.path=path;
        this.fileName=path.getFileName().toString();
        this.parentDirectoryPath = new File(path.toString()).getParent().substring(path.toString().lastIndexOf("\\") + 1, path.toString().length());
        this.size=attrs.size();
        this.creationTime=attrs.creationTime().toMillis();
        this.modificationTime=attrs.lastModifiedTime().toMillis();
        this.isSymbolic=attrs.isSymbolicLink();
    }

    public Fil() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fil fil = (Fil) o;

        return getPath().equals(fil.getPath());

    }

    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

    @Override
    public String toString() {
        return "Fil "+path.toString();
    }

    @Override
    public boolean isSameFile(IFile other) {
        return path.equals(other.getPath());
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
        return path;
    }

    @Override
    public boolean isDir() {
        return false;
    }
}
