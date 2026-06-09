package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.base.eval.{AbstractEval, Evaluator}
import io.github.mathter.zi.dsl.{Dsl, NumericSourceOps, Source}
import io.github.mathter.zi.eval.Context

implicit class ShortSourceOps(x: Source[Short]) extends NumericSourceOps[Short] {
  implicit private val dsl: Dsl = x.dsl

  override def +(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => (x + y).toShort))
  }

  override def -(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => (x - y).toShort))
  }

  override def *(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => (x * y).toShort))
  }

  override def /(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => (x / y).toShort))
  }

  override def %(y: Source[Short]): Source[Short] = new AbstractEval[Short] {
    override def evalI(implicit context: Context): Opt[Short] =
      Evaluator.evalSource(x).flatMap(x => Evaluator.evalSource(y).map(y => (x % y).toShort))
  }
}
