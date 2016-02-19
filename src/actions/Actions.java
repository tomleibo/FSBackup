package actions;

import data.Dir;
import data.Fil;
import data.Scan;
import utils.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public interface Actions {
    //<T extends FilOrDir> Collection<T> operate(Collection<T> src, Collection<T> dest, BiPredicate<T,T> predicate,Function<Pair<T,T>,T> f);
    static final Logger log = Logger.getLogger(Actions.class.getName());

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

    static Collection<Pair<Fil,Fil>> keepChangedFils(Map<Fil,Fil> src, Map<Fil,Fil> dest) {
        return collect(src,dest,(Fil1,Fil2)->Fil1.getModificationDate() == Fil2.getModificationDate());
    }

    static void keepChangedDirs(Scan scan,Dir src,Dir dest) {
        if (!src.equals(dest)) {
            throw new RuntimeException("dirs are not equal.");
        }
        if (scan==null) {
            throw new NullPointerException("scan is null");
        }
        Dir dir2=null;
        Collection<Pair<Fil, Fil>> Fils = collectChangedFilsOfOneDir(src, dest);
        if (Fils != null && !Fils.isEmpty()) {
            scan.add(Fils);
            scan.add(src,dest);
        }
        if (src.getDirs()==null) {
            return;
        }
        for (Dir dir : src.getDirs().keySet()) {
            dir2 = dest.getDirs().get(dir);
            if (dir2==null) {
                scan.add(dir,null);
            }
            else {
                keepChangedDirs(scan, dir, dir2);
            }
        }
    }

    static Collection<Pair<Fil, Fil>> collectChangedFilsOfOneDir(Dir src, Dir dest) {
        Map<Fil,Fil> srcFils = new HashMap<>();
        try {
            for (File file : src.getFiles()) {
                srcFils.put(new Fil(file, src.getBasePath()), new Fil(file, src.getBasePath()));
            }
            Map<Fil, Fil> destFils = new HashMap<>();
            for (File file : dest.getFiles()) {
                destFils.put(new Fil(file, dest.getBasePath()), new Fil(file, dest.getBasePath()));
            }
            return (keepChangedFils(srcFils, destFils));
        }
        catch (IOException e ) {
            log.log(Level.WARNING,"creating files created an IOE.");
            log.log(Level.WARNING, e.getLocalizedMessage());
            return new ArrayList<>();
        }

    }

}
