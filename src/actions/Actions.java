package actions;

import data.Fil;
import utils.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by thinkPAD on 1/11/2016.
 */
public interface Actions {
    //<T extends FileOrDir> Collection<T> operate(Collection<T> src, Collection<T> dest, BiPredicate<T,T> predicate,Function<Pair<T,T>,T> f);

    public static <T> Collection<T> collect(Collection<T> src, Collection<T> dest, BiPredicate<T, T> predicate,
    Function<Pair<T,T>,T> f) {
        Collection<T> result = new ArrayList<>();
        for (T f1: src){
            for (T f2:dest){
                if (predicate.test(f1,f2)) {
                    result.add(f.apply(new Pair<T, T>(f1,f2)));
                }
            }
        }
        return result;
    }

    public static Collection<Fil> copyChangedFiles(Collection<Fil> src, Collection<Fil> dest) {
        /*

        complete last argument



         */
        return collect(src,dest,(file1,file2)->file1.isSameFile(file2)&&file1.isChanged(file2),(p)->new Fil());
    }
}
