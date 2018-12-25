package de.techfak.gse.ysander.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void cache() {
        AtomicInteger called = new AtomicInteger();
        Cache<Integer> cache = Cache.cache(called::incrementAndGet);
        assertEquals(1, (int) cache.result()); // shuld be 1 as called once
        assertEquals(1, (int) cache.result()); // should still be one as cache is used now
    }

    @Test
    void result() {
        Cache<Boolean> cache = Cache.cache(() -> true);
        assertTrue(cache.result());
    }
}
