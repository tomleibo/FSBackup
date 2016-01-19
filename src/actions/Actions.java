package actions;

import data.Dir;
import data.Fil;
import data.FileOrDir;
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

    public static <T extends FileOrDir> Collection<T> operate(Collection<T> src, Collection<T> dest, BiPredicate<T, T> predicate,
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
        Collection<Fil> result = new ArrayList<>();
        /*****
         *
         *
         * Call Operate with lambda expression.
         *
         *
         *
         */
        return result;
    }
}
