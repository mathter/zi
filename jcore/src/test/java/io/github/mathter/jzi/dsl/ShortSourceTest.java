package io.github.mathter.jzi.dsl;

import io.github.mathter.jzi.dsl.base.BaseDsl;
import io.github.mathter.zi.data.PathMap;
import io.github.mathter.zi.dsl.base.eval.BaseContext;
import io.github.mathter.zi.dsl.base.eval.Evaluator;
import io.github.mathter.zi.eval.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShortSourceTest {
    @Test
    public void testPlus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.literal((short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.plus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSupplierPlus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.numberLiteral(() -> (short) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.numberLiteral(() -> (short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.plus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMinus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.literal((short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.minus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) -10, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMultiply() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.literal((short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.multiply(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 200, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testDivide() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 30);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.literal((short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.divide(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 1, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testRem() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 30);
        Assertions.assertNotNull(l);
        final NumberSource<Short> r = dsl.literal((short) 20);
        Assertions.assertNotNull(r);
        final NumberSource<Short> s = l.rem(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 10, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testAbs() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Short> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testNegate() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Short> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 30, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignLeft() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) -30);
        Assertions.assertNotNull(l);
        final NumberSource<Short> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) -1, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignZero() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 0);
        Assertions.assertNotNull(l);
        final NumberSource<Short> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 0, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignRight() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Short> l = dsl.literal((short) 10);
        Assertions.assertNotNull(l);
        final NumberSource<Short> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals((short) 1, Evaluator.evalSource(s, context).get());
    }
}
