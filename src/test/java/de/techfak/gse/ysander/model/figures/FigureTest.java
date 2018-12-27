package de.techfak.gse.ysander.model.figures;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import de.techfak.gse.ysander.model.Field;
import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.rules.Hint;
import de.techfak.gse.ysander.model.rules.MoveHint;
import de.techfak.gse.ysander.model.rules.ThreatHint;
import org.junit.jupiter.api.BeforeEach;

public class FigureTest {

    private Field from;


    @BeforeEach
    void setUp() {
        this.setFrom(new Field(3, 3));
    }

    protected void setFrom(Field from) {
        this.from = from;
    }

    protected <T extends Hint> Set<Hint> filter(Class<T> type, Set<? extends Hint> hints) {
        return hints.stream().filter(h -> h.getClass().equals(type)).collect(Collectors.toSet());
    }

    protected ThreatHint threatAt(Field target) {
        return new ThreatHint(new Move(this.from, target));
    }

    protected MoveHint moveAt(Field target) {
        return new MoveHint(new Move(this.from, target));
    }

    protected Set<Hint> hintSetFrom(Hint... hints) {
        return Arrays.stream(hints).collect(Collectors.toSet());
    }

}
