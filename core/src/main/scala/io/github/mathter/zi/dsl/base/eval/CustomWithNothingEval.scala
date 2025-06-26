package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.eval.{Context, Eval}

class CustomWithNothingEval[T, D](val eval: Eval[T], f: T => D) extends AbstractEval[D] {
  override def evalI(using context: Context): D = {
    val value = this.eval.eval

    f.apply(value)
  }
}
