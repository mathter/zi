package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.Context

class LiteralEval[T](val literal: T)(implicit dsl: Dsl) extends AbstractEval[T] {
  override def evalI(context: Context): Opt[T] = Opt(this.literal)
}
