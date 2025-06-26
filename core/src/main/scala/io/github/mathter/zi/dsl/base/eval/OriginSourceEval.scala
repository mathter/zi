package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.Context

class OriginSource extends AbstractEval[PathMap] with Source[PathMap] {
  override def evalI(context: Context): PathMap = context.origin
}
