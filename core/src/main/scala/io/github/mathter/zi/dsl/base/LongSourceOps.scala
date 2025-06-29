package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Evaluator}

implicit class LongSourceOps(x: Source[Long]) extends NumericSourceOps[Long] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Long]): Source[Long] = new AbstractEval[Long] {
    override def evalI(implicit context: Context): Opt[Long] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x + y))
  }

  override def -(y: Source[Long]): Source[Long] = new AbstractEval[Long] {
    override def evalI(implicit context: Context): Opt[Long] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x - y))
  }

  override def *(y: Source[Long]): Source[Long] = new AbstractEval[Long] {
    override def evalI(implicit context: Context): Opt[Long] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x * y))
  }

  override def /(y: Source[Long]): Source[Long] = new AbstractEval[Long] {
    override def evalI(implicit context: Context): Opt[Long] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x / y))
  }

  override def %(y: Source[Long]): Source[Long] = new AbstractEval[Long] {
    override def evalI(implicit context: Context): Opt[Long] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => x % y))
  }
}
