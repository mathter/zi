package io.github.mathter.zi.eval.base

import io.github.mathter.zi.eval.Context

class LiteralEval[T](val f: () => T) extends AbstractEval[T] {

  override def evalI(context: Context): T = f()
}
