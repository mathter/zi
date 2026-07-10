import io.github.mathter.jzi.dsl.Dsl;
import io.github.mathter.jzi.dsl.NumberSource;
import io.github.mathter.jzi.dsl.base.BaseDsl;
import io.github.mathter.zi.data.PathMap;
import io.github.mathter.zi.dsl.base.eval.BaseContext;
import io.github.mathter.zi.dsl.base.eval.Evaluator;
import io.github.mathter.zi.eval.Context;

public class Main {
    public static void main(String[] args) {
        final Context context = new BaseContext(PathMap.empty());
        final Dsl dsl = new BaseDsl();

        final NumberSource<Integer> l = dsl.literal(10);
        final NumberSource<Integer> r = dsl.literal(20);

        final NumberSource<Integer> s = l.plus(r);

        System.out.println(Evaluator.evalSource(s, context));
    }
}
