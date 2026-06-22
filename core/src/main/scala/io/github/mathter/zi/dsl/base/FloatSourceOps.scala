package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.{AbstractEval, Evaluator}
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Tracer}

private class FloatSourceOps(x: Source[Float])(implicit tracer: Tracer) extends NumericSourceOps[Float] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Float]): Source[Float] = new AbstractEval[Float] {
    override def evalI(implicit context: Context): Opt[Float] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x + y))
  }

  override def -(y: Source[Float]): Source[Float] = new AbstractEval[Float] {
    override def evalI(implicit context: Context): Opt[Float] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x - y))
  }

  override def *(y: Source[Float]): Source[Float] = new AbstractEval[Float] {
    override def evalI(implicit context: Context): Opt[Float] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x * y))
  }

  override def /(y: Source[Float]): Source[Float] = new AbstractEval[Float] {
    override def evalI(implicit context: Context): Opt[Float] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x / y))
  }

  override def %(y: Source[Float]): Source[Float] = new AbstractEval[Float] {
    override def evalI(implicit context: Context): Opt[Float] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x % y))
  }
}
