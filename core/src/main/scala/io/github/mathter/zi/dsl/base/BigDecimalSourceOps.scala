package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.{AbstractEval, Evaluator}
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.Context

private class BigDecimalSourceOps(x: Source[BigDecimal]) extends NumericSourceOps[BigDecimal] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x + y))
  }

  override def -(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x - y))
  }

  override def *(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x * y))
  }

  override def /(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x / y))
  }

  override def %(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => x % y))
  }
}
