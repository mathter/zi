package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Composite, Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

private class CompositeEval[T, T0](val t: Eval[T], val t0: Eval[T0])(implicit dsl: Dsl) extends Composite[T, T0] {
  override def fun[D](f: (T, T0) => D)(implicit ctag: ClassTag[D]): Source[D] = new AbstractEval[D] {
    override def evalI(implicit context: Context): Opt[D] = t.eval.flatMap(t => t0.eval.map(t0 => f.apply(t, t0)))
  }
}
