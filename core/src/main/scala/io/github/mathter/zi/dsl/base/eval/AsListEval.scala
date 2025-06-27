package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.{Context, Eval}

class AsListEval[T](val eval: Eval[T]) extends AbstractEval[List[T]] {
  override def evalI(using context: Context): Opt[List[T]] = this.eval.eval.map(List(_))
}
