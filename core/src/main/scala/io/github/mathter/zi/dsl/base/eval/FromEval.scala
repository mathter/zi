package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Dsl, From, Source}
import io.github.mathter.zi.eval.{Context, Eval}
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

class FromEval[T](val f: Context => Opt[PathMap], val path: Path)(implicit dsl: Dsl, ctag: ClassTag[T]) extends From[T] {
  override def from(source: Source[T]): Source[T] & Terminal = new Terminal(source.asInstanceOf[Eval[T]])

  class Terminal(val from: Eval[T]) extends AbstractEval[T], io.github.mathter.zi.eval.Terminal {
    override def evalI(using context: Context): Opt[T] = {
      val map = f.apply(context)
      val option = this.from.eval

      option.foreach(map.get(path) = _)

      option
    }
  }
}
