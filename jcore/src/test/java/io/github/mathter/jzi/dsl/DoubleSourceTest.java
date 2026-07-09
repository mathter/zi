package io.github.mathter.jzi.dsl;

import io.github.mathter.jzi.dsl.base.BaseDsl;
import io.github.mathter.zi.data.PathMap;
import io.github.mathter.zi.dsl.base.eval.BaseContext;
import io.github.mathter.zi.dsl.base.eval.Evaluator;
import io.github.mathter.zi.eval.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleSourceTest {
    @Test
    public void testPlus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(10d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> r = dsl.literal(20d);
        Assertions.assertNotNull(r);
        final NumberSource<Double> s = l.plus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals(30d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMinus() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(10d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> r = dsl.literal(20d);
        Assertions.assertNotNull(r);
        final NumberSource<Double> s = l.minus(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals(-10f, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testMultiply() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(10d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> r = dsl.literal(20d);
        Assertions.assertNotNull(r);
        final NumberSource<Double> s = l.multiply(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals(200d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testDivide() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(30d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> r = dsl.literal(20d);
        Assertions.assertNotNull(r);
        final NumberSource<Double> s = l.divide(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals(1.5d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testRem() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(30d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> r = dsl.literal(20d);
        Assertions.assertNotNull(r);
        final NumberSource<Double> s = l.rem(r);
        Assertions.assertNotNull(s);

        Assertions.assertEquals(10d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testAbs() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(-30d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals(30d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testNegate() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(-30d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> s = l.abs();
        Assertions.assertNotNull(s);

        Assertions.assertEquals(30d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignfeft() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(-30d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals(-1d, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignZero() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(0d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals(0f, Evaluator.evalSource(s, context).get());
    }

    @Test
    public void testSignRight() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());

        final NumberSource<Double> l = dsl.literal(10d);
        Assertions.assertNotNull(l);
        final NumberSource<Double> s = l.sign();
        Assertions.assertNotNull(s);

        Assertions.assertEquals(1f, Evaluator.evalSource(s, context).get());
    }
}
