package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.eval.Context

class OriginSourceEval extends AbstractEval[PathMap] with Source[PathMap] {
  override def evalI(context: Context): PathMap = context.origin
}
