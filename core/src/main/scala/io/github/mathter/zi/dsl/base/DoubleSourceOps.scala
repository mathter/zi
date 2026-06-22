package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.{AbstractEval, Evaluator}
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Tracer}

private class DoubleSourceOps(x: Source[Double])(implicit tracer: Tracer) extends NumericSourceOps[Double] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x + y))
  }

  override def -(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x - y))
  }

  override def *(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x * y))
  }

  override def /(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x / y))
  }

  override def %(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x % y))
  }
}
