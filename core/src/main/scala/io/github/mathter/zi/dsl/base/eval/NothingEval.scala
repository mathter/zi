package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.eval.Context

class NothingEval[T] extends AbstractEval[T] {
  override def evalI(context: Context): T = Option.empty[T].asInstanceOf[T]
}
