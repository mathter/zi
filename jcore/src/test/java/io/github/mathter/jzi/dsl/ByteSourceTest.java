package io.github.mathter.jzi.dsl;

import io.github.mathter.jzi.dsl.base.BaseDsl;
import io.github.mathter.zi.data.PathMap;
import io.github.mathter.zi.dsl.base.eval.BaseContext;
import io.github.mathter.zi.dsl.base.eval.Evaluator;
import io.github.mathter.zi.eval.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteSourceTest {
    @Test
    public void testPlus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal((byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.plus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSupplierPlus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal(() -> (byte) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal(() -> (byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.plus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMinus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal((byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.minus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) -10, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMultiply() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal((byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.multiply(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 200, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testDivide() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 30);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal((byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.divide(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 1, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testRem() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 30);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> r = dsl.literal((byte) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Byte> s = l.rem(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 10, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testAbs() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testNegate() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignLeft() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) -1, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignZero() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 0);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 0, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignRight() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Byte> l = dsl.literal((byte) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Byte> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((byte) 1, Evaluator.evalSource(s, context).get());
    }
}
