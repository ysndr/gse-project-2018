package de.techfak.gse.ysander.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public class Cache<V> {

    private final Supplier<V> func;
    private V result;
    private boolean calculated;

    private Cache(final Supplier<V> func) {
        this.func = func;
        this.calculated = false;
    }

    public static <V>  Cache<V> cache(Supplier<V> func) {
        return new Cache<>(func);
    }

    public V result() {
        if (!this.calculated) {
            result = func.get();
            calculated = true;
        }
        return result;
    }

}
