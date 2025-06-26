package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.eval.Context

class LiteralEval[T](val f: () => T) extends AbstractEval[T] {

  override def evalI(context: Context): T = f()
}
