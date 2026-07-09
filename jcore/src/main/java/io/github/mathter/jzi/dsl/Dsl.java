package io.github.mathter.jzi.dsl;

public interface Dsl extends io.github.mathter.zi.dsl.Dsl {
    public NumberSource<Byte> literal(Byte value);

    public NumberSource<Short> literal(Short value);

    public NumberSource<Integer> literal(Integer value);

    public NumberSource<Long> literal(Long value);

    public NumberSource<Float> literal(Float value);

    public NumberSource<Double> literal(Double value);
}
