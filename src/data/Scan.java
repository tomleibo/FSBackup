package data;

import utils.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by thinkPAD on 2/17/2016.
 */
public class Scan {
    Date date;
    Collection<Pair<File,File>> changedFiles; // (in all folder tree)
    Collection<Pair<Dir,Dir>> changedDirs;

    public Scan() {
        date=new Date();
        changedDirs = new ArrayList<>();
        changedFiles =new ArrayList<>();
    }

    public void add(Dir src,Dir dest) {
        changedDirs.add(new Pair<>(src,dest));
    }

    public void add(File src, File dest) {
        changedFiles.add(new Pair<>(src,dest));
    }

    public void add(Collection<Pair<File,File>> files) {
        for (Pair p : files) {
            changedFiles.add(p);
        }
    }
}
