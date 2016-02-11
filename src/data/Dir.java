package data;

import interfaces.IFile;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public class Dir implements IFile {
    //TODO: building hashset with initial capacity will increase performance.


    Path path;
    Map<IFile,IFile> files;
    boolean isLazy;

    public Dir(Path path, BasicFileAttributes attrs) {
        this.path=path;
        this.isLazy=true;
        this.files = new HashMap<>();
    }

    public boolean addFile (IFile file) {
        return files.put(file,file)==null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dir dir = (Dir) o;

        return getPath().equals(dir.getPath());

    }

    @Override
    public boolean isSameFile(IFile other) {
        return path.equals(other.getPath()) && (this.isDir() == other.isDir());
    }

    public Map<IFile,IFile> getFiles(IFile file) {
        return files;
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
        return null;
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

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Dir: "+path.toString()+"\n");
        for (IFile file : files.keySet()){
            if (file.isDir()) {
                String[] lines = file.toString().split("\n");
                for (String line:lines) {
                    sb.append("\t"+line+"\n");
                }
            }
            else {
                sb.append("File: " + path.toString()+"\n");
            }
        }
        return sb.toString();
    }
}
