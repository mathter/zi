package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Destination, From}
import io.github.mathter.zi.eval.{Context, Eval}
import io.github.mathter.zi.path.Path

class DestinationEval(val tag: Eval[Any]) extends AbstractEval[PathMap], Destination {
  override def evalI(context: Context): Opt[PathMap] = Opt(context.destination(this.tag.eval(context)))

  override def by[T](path: Path): From[T] = new FromEval[T](this.evalI, path)
}
