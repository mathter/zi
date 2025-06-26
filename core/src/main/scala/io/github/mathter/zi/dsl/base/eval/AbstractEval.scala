package io.github.mathter.zi.eval.base

import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

abstract class AbstractEval[T](val dsl: Dsl) extends Eval[T] with Source[T] {
  override def dsl: Dsl = ???

  override def map[D](f: Source[T] => Source[D]): Source[D] = ???

  override def eval(implicit context: Context): T = {
    this.evalI(context)
  }

  def evalI(context: Context): T
}
