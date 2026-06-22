package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval, Tracer}
import io.github.mathter.zi.path.Path

class PathMapByPathAcceptor[T](val eval: Eval[PathMap], val path: Path)(implicit dsl: Dsl, tracer: Tracer) extends AbstractAcceptorEval[T]((opt, context) => {
  eval.eval(context).flatMap(pathMap => opt.map(e => {
    pathMap.put(path, e)
    e
  }))
}) {
  override def evalI(implicit context: Context): Opt[T] = {
    this.eval.eval.flatMap(e => e.get(this.path))
  }
}
