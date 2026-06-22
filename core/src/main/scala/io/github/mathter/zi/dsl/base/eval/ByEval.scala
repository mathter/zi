package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval, Tracer}
import io.github.mathter.zi.path.Path

class ByEval[T](val eval: Eval[PathMap], val path: Path)(implicit dsl: Dsl, tracer: Tracer) extends AbstractEval[T] {
  override def evalI(using context: Context): Opt[T] = this.eval.eval.flatMap(pathMap => pathMap(path))
}
