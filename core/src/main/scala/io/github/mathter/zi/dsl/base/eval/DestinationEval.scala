package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Destination, Dsl, From}
import io.github.mathter.zi.eval.{Context, Eval}
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

class DestinationEval(val tag: Eval[Any])(implicit dsl: Dsl) extends AbstractEval[PathMap], Destination {
  override def evalI(implicit context: Context): Opt[PathMap] = Opt(context.destination(this.tag.eval))

  override def by[T](path: Path)(implicit ctag: ClassTag[T]): From[T] = new FromEval(this.evalI, path)
}
