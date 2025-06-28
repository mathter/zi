package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.eval.Context

class OriginSourceEval(implicit dsl: Dsl) extends AbstractEval[PathMap] with Source[PathMap] {
  override def evalI(context: Context): Opt[PathMap] = Opt(context.origin)
}
