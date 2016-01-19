package utils;

/**
 * Created by thinkPAD on 1/20/2016.
 */
public class Pair<K,V> {
    K k;
    V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (k != null ? !k.equals(pair.k) : pair.k != null) return false;
        return !(v != null ? !v.equals(pair.v) : pair.v != null);

    }

    @Override
    public int hashCode() {
        int result = k != null ? k.hashCode() : 0;
        result = 31 * result + (v != null ? v.hashCode() : 0);
        return result;
    }
}
