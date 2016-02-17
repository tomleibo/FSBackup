package core;

import fileVisitors.AbstractFileVisitor;
import fileVisitors.ReturnAllFilesVisitor;
import interfaces.IFile;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO in Actions.
 * implement Fil
 *
 * Some ideas:
 * 1. create a basic action API containing: copy + override, move non-existent, incremental backup (based on diff), delete duplicates
 * 3. create a higher level of API for choosing which dirs to backup to where.
 * 4. optional: standardization of common drives: google drive, dropbox etc...
 * 5. optional include communication for non-desktop cloud services.
 * 6. save previous scans in DB. use MongoDB
 * 7. view them in gui. allow all high API calls from gui.
 */
public class API {
    protected String srcDir;
    protected String destDir;
    protected AbstractFileVisitor visitor;
    protected Method action;
    protected AbstractFileVisitor srcVisitor;
    protected AbstractFileVisitor destVisitor;
    protected Set<IFile> srcFiles = new HashSet<>();
    protected Set<IFile> destFiles=new HashSet<>();

    public static API sync() {
        API api = new API();
        return api;
    }

    public API from(String dir) {
        this.srcDir=dir;
        this.srcVisitor = new ReturnAllFilesVisitor(srcFiles);
        return this;

    }

    public API to(String destDir) {
        this.destDir=destDir;
        this.destVisitor = new ReturnAllFilesVisitor(destFiles);
        return this;
    }

    public void go() {
        Crawler.crawl(srcDir,false,-1,srcVisitor);
        Crawler.crawl(destDir,false,-1,destVisitor);

    }
}
