package actions;

import data.Dir;
import data.Fil;
import data.Scan;
import utils.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public interface Actions {
    //<T extends FileOrDir> Collection<T> operate(Collection<T> src, Collection<T> dest, BiPredicate<T,T> predicate,Function<Pair<T,T>,T> f);


    //TODO what if dest is null
    static <T> Collection<Pair<T,T>> collect(Map<T,T> src, Map<T,T> dest, BiPredicate<T, T> predicate) {
        Collection<Pair<T,T>> result = new ArrayList<>();
        for (T f1: src.keySet()){
            T f2 = dest.get(f1);
            if (f2==null) {
                result.add(new Pair<T,T>(f1,null));
            }
            else if (predicate.test(f1,f2)) {
                result.add(new Pair<T, T>(f1,f2));
            }
        }
        return result;
    }

    static Collection<Pair<File,File>> keepChangedFiles(Map<File,File> src, Map<File,File> dest) {
        return collect(src,dest,(file1,file2)->file1.lastModified() == file2.lastModified());
    }

    static void keepChangedDirs(Scan scan,Dir src,Dir dest) {
        if (!src.isSameFile(dest)) {
            throw new RuntimeException("dirs are not equal.");
        }
        Dir dir2=null;
        Collection<Pair<File, File>> files = collectChangedFilesOfOneDir(src, dest);
        if (files != null && !files.isEmpty()) {
            scan.add(files);
            scan.add(src,dest);
        }
        //TODO complete this. at return scan should contain all files that should be diffed and patched.
        // dirs are all the dirs with changes. not for any functionality just for auditing.
        // complete the algorithm for dirs.
        for (Dir dir : src.getDirs().keySet()) {
            dir2 = dest.getDirs().get(dir);
            if (dir2==null) {
                scan.add(dir,null);
            }

            keepChangedDirs(scan,dir,dir2);
        }
    }

    static Collection<Pair<File, File>> collectChangedFilesOfOneDir(Dir src, Dir dest) {
        Map<File,File> srcFiles = new HashMap<>();
        for (File file:src.getFiles()) {
            srcFiles.put(file,file);
        }
        Map<File,File> destFiles = new HashMap<>();
        for (File file:dest.getFiles()) {
            destFiles.put(file,file);
        }
        return (keepChangedFiles(srcFiles,destFiles));
    }

}
