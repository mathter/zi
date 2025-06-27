package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Composite, Source}
import io.github.mathter.zi.eval.{Context, Eval}

private class CompositeEval[T, T0](val t: Eval[T], val t0: Eval[T0]) extends Composite[T, T0] {
  override def fun[D](f: (T, T0) => D): Source[D] = new AbstractEval[D] {
    override def evalI(context: Context): Opt[D] = t.eval(context).flatMap(t => t0.eval(context).map(t0 => f.apply(t, t0)))
  }
}
