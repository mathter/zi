package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Evaluator}

implicit class BigDecimalSourceOps(x: Source[BigDecimal]) extends NumericSourceOps[BigDecimal] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x + y))
  }

  override def -(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x - y))
  }

  override def *(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x * y))
  }

  override def /(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x / y))
  }

  override def %(y: Source[BigDecimal]): Source[BigDecimal] = new AbstractEval[BigDecimal] {
    override def evalI(implicit context: Context): Opt[BigDecimal] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x % y))
  }
}
