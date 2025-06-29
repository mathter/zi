package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Evaluator}

implicit class ShortSourceOps(x: Source[Short]) extends NumericSourceOps[Short] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => (x + y).toShort))
  }

  override def -(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => (x - y).toShort))
  }

  override def *(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => (x * y).toShort))
  }

  override def /(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => (x / y).toShort))
  }

  override def %(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.eval(x).flatMap(x => Evaluator.eval(y).map(y => (x % y).toShort))
  }
}
