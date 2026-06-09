package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.Context

class PathMapAcceptor(implicit dsl: Dsl) extends AbstractAcceptorEval[PathMap](
  (opt, context) => opt.map(e => {
    AbstractEval.cachePut(opt, context)
    e
  })
) {
  override def evalI(using context: Context): Opt[PathMap] =
    AbstractEval.cacheGetOrElseUpdate(Opt(PathMap.empty), context)
}
