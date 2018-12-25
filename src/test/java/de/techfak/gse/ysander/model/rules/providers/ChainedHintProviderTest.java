package de.techfak.gse.ysander.model.rules.providers;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.State;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.util.Cache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainedHintProviderTest {

    @Test
    void chain() {
        AtomicInteger counter = new AtomicInteger();

        HintProvider A = createHintPrivider(0,2, counter);
        HintProvider B = createHintPrivider(1, 0, counter);

        A.chain(B).getHints(null);

        assertEquals(12, counter.get());

        counter.set(0);
        Cache<Set<? extends Hint>> C = A.chain(B).getHintsCached(null);
        C.result();
        C.result(); // should be cached here;


        assertEquals(12, counter.get());






    }

    private HintProvider createHintPrivider(int x ,int y, AtomicInteger counter) {
        return (final State state) -> {

                Set<Hint> set = new HashSet<>();
                counter.addAndGet(x*10 + y);
                set.add(new Hint() {
                    @Override
                    public Field target() {
                        return new Field(x, y);
                    }

                    @Override
                    public State apply(final State state) {

                        return state;
                    }
                });

                return set;
            };
    }

    @Test
    void getHints() {
    }
}
