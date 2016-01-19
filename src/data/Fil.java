package data;

import java.nio.file.Path;

/**
 * Created by thinkPAD on 1/9/2016.
 */
public class Fil implements FileOrDir{
    protected Path path;
    protected String fileId;
    protected String fileName;
    protected String parentDirectoryId;
    protected String parentDirectoryPath;
    protected String sourceDirectoryId;
    protected String sourceDirectoryPath;
    protected String owner;
    protected String scanTime;
    protected boolean isRemoved;

    protected String operatingSystem;
    protected long size;
    protected String creationTime;
    protected String modificationTime;
    protected String contentType;
    protected Boolean isSymbolic;

    public Fil() {
    }

    public Fil(Path path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Fil "+path.toString();
    }
}
