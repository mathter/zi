package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.{Context, Eval}

class CustomEval[T, D](val eval: Eval[T], f: Opt[T] => Opt[D]) extends AbstractEval[D] {
  override def evalI(using context: Context): Opt[D] = {
    val option = this.eval.eval

    this.f.apply(option)
  }
}
