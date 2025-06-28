package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval}

class AsListSourceEval[E](val eval: Eval[E])(implicit dsl: Dsl) extends AbstractEval[List[E]] {
  override def evalI(implicit context: Context): Opt[List[E]] = this.eval.eval.map(List(_))
}
