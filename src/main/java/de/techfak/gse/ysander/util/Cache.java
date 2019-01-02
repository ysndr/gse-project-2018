package de.techfak.gse.ysander.util;

import java.util.function.Supplier;

/**
 * A simple Function cache.
 * Stores a (hopefully) pure function, calculates its return value lazily and
 * stores it so subsequent calls dont need to calculate the value again
 *
 * @param <V>
 */
public final class Cache<V> {

    private final Supplier<V> func;

    private V result;

    private boolean calculated;

    private Cache(final Supplier<V> func) {
        this.func = func;
        this.calculated = false;
    }

    /**
     * Create a cache for the given Supplier function.
     *
     * @param func the function to cache
     * @param <V>  The Type of the cached return value
     * @return a cache instance
     */
    public static <V> Cache<V> cache(Supplier<V> func) {
        return new Cache<>(func);
    }

    /**
     * Calculate the result if not cached, or return cached value.
     *
     * @return cached value
     */
    public V result() {
        if (!this.calculated) {
            result = func.get();
            calculated = true;
        }
        return result;
    }

}
