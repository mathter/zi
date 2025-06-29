package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Evaluator}

implicit class DoubleSourceOps(x: Source[Double]) extends NumericSourceOps[Double] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x + y))
  }

  override def -(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x - y))
  }

  override def *(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x * y))
  }

  override def /(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x / y))
  }

  override def %(y: Source[Double]): Source[Double] = new AbstractEval[Double] {
    override def evalI(implicit context: Context): Opt[Double] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x % y))
  }
}
