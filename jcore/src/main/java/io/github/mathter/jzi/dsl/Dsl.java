package io.github.mathter.jzi.dsl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

public interface Dsl extends io.github.mathter.zi.dsl.Dsl {
    public NumberSource<Byte> literal(Byte value);

    public NumberSource<Short> literal(Short value);

    public NumberSource<Integer> literal(Integer value);

    public NumberSource<Long> literal(Long value);

    public NumberSource<Float> literal(Float value);

    public NumberSource<Double> literal(Double value);

    public NumberSource<BigInteger> literal(BigInteger value);

    public NumberSource<BigDecimal> literal(BigDecimal value);

    public <T extends Number> NumberSource<T> literal(Supplier<T> supplier);
}
