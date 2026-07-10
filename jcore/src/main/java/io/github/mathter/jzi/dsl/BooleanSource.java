package io.github.mathter.jzi.dsl;

import io.github.mathter.zi.dsl.Source;
import io.github.mathter.zi.dsl.Then;

public interface BooleanSource extends Source<Boolean> {
    public BooleanSource and(Source<Boolean> other);

    public BooleanSource or(Source<Boolean> other);

    public BooleanSource xor(Source<Boolean> other);

    public BooleanSource not();

    public <T> Then<T> then(Source<T> source);
}
