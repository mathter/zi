package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Tracer}

class PathMapAcceptor(val accept: LocalAccept = new LocalAccept())(implicit dsl: Dsl, tracer: Tracer) extends AbstractAcceptorEval[PathMap](accept) {
  override def evalI(using context: Context): Opt[PathMap] = {
    Opt(this.accept.pathMap)
  }
}

class LocalAccept(var pathMap: PathMap = PathMap.empty) extends Accept[PathMap] {
  override def apply(opt: Opt[PathMap], context: Context): Opt[PathMap] = {
    opt.map(e => {
      this.pathMap = e
      e
    })
  }
}
