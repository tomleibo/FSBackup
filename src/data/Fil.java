package data;

import interfaces.IFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class Fil {
    protected Path basePath;
    protected Path relPath;
    protected String fileName;

    protected long size;
    protected long creationTime;
    protected long modificationTime;
    protected Boolean isSymbolic;


    public Fil(Path basePath, Path fullPath, BasicFileAttributes attrs) {
        this.basePath = basePath;
        this.relPath = basePath.relativize(fullPath);
        this.fileName= relPath.getFileName().toString();
        this.size=attrs.size();
        this.creationTime=attrs.creationTime().toMillis();
        this.modificationTime=attrs.lastModifiedTime().toMillis();
        this.isSymbolic=attrs.isSymbolicLink();
    }

    public Fil(File file, Path basePath) throws IOException {
        this(basePath,file.toPath(),Files.readAttributes(file.toPath(),BasicFileAttributes.class));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fil fil = (Fil) o;

        return getRelPath().equals(fil.getRelPath());

    }

    @Override
    public int hashCode() {
        return getRelPath().hashCode();
    }

    @Override
    public String toString() {
        return "Fil "+ relPath.toString();
    }

    public boolean isSameFile(Fil other) {
        return relPath.equals(other.getRelPath()) && (this.isDir() == other.isDir());
    }

    public boolean isChanged(Fil otherFile) {
        return isSameFile(otherFile) && getModificationDate() != (otherFile.getModificationDate());
    }

    public long getModificationDate() {
        return modificationTime;
    }
/*
    @Override
    public byte[] getHash() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            //never happens.
        }
        try (InputStream is = Files.newInputStream(relPath);
             DigestInputStream dis = new DigestInputStream(is, md))
        {
            int numRead;
            do {
                byte[] buffer = new byte[1024];
                numRead = dis.read(buffer);
            } while (numRead != -1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest();
        return digest;
    }
*/

    public Path getRelPath() {
        return relPath;
    }


    public boolean isDir() {
        return false;
    }
}
