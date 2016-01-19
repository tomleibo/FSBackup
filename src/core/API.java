package core;

import actions.Actions;
import data.Fil;
import fileVisitors.IFileVisitor;
import fileVisitors.ReturnAllFilesVisitor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Some ideas:
 * 1. create a basic action API containing: move, copy, delete, copy & override
 * 2. create a higher level of API above it containing: full backup, differential backup, incremental backup.
 * 3. create a higher level of API for choosing which dirs to backup to where.
 * 4. optional: standardization of common drives: google drive, dropbox etc...
 * 5. optional include communication for non-desktop cloud services.
 * 6. save previous scans in DB. use MongoDB
 * 7. view them in gui. allow all high API calls from gui.
 */
public class API {
    protected String srcDir;
    protected String destDir;
    protected IFileVisitor visitor;
    protected Method action;
    protected IFileVisitor srcVisitor;
    protected IFileVisitor destVisitor;
    protected List<Fil> srcFiles = new ArrayList<>();
    protected List<Fil> destFiles=new ArrayList<>();

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
