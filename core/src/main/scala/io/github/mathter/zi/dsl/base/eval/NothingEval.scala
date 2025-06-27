package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.Context

class NothingEval[T] extends AbstractEval[T] {
  override def evalI(context: Context): Opt[T] = Opt.empty[T]
}
