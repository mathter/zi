package io.github.mathter.jzi.dsl;

import io.github.mathter.jzi.dsl.base.BaseDsl;
import io.github.mathter.zi.data.PathMap;
import io.github.mathter.zi.dsl.base.eval.BaseContext;
import io.github.mathter.zi.dsl.base.eval.Evaluator;
import io.github.mathter.zi.eval.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringSourceTest {
    @Test
    public void testToUpperCase() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = "Wwert0_ewewE";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final StringSource r = s.toUpperCase();
        Assertions.assertNotNull(r);

        Assertions.assertEquals(origin.toUpperCase(), Evaluator.evalSource(r, context).get());
    }

    @Test
    public void testToLowerCase() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = "Wwert0_ewewE";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final StringSource r = s.toLowerCase();
        Assertions.assertNotNull(r);

        Assertions.assertEquals(origin.toLowerCase(), Evaluator.evalSource(r, context).get());
    }

    @Test
    public void testReplaceAll() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = "This is a test";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final StringSource r = s.replaceAll("T\\S*s", "That");
        Assertions.assertNotNull(r);
        Assertions.assertEquals(origin.replaceAll("T\\S*s", "That"), Evaluator.evalSource(r, context).get());
    }

    @Test
    public void testLength() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = "This is a test";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final NumberSource<Integer> l = s.length();
        Assertions.assertNotNull(l);
        Assertions.assertEquals(origin.length(), Evaluator.evalSource(l, context).get());
    }

    @Test
    public void testTrim() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = " for trim ";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final StringSource r = s.trim();
        Assertions.assertNotNull(r);
        Assertions.assertEquals(origin.trim(), Evaluator.evalSource(r, context).get());
    }

    @Test
    public void testIsEmpty() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = " for trim ";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final BooleanSource r = s.isEmpty();
        Assertions.assertNotNull(r);
        Assertions.assertFalse(Evaluator.evalSource(r, context).get());
    }

    @Test
    public void testIsNonEmpty() {
        final Dsl dsl = new BaseDsl();
        final Context context = new BaseContext(PathMap.empty());
        final String origin = " for trim ";

        final StringSource s = dsl.literal(origin);
        Assertions.assertNotNull(s);

        final BooleanSource r = s.isNotEmpty();
        Assertions.assertNotNull(r);
        Assertions.assertTrue(Evaluator.evalSource(r, context).get());
    }
}
