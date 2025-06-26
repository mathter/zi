package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.eval.{Context, Eval}

class MapEval[T, D](val source: Source[T], f: Source[T] => Source[D]) extends AbstractEval[D] {
  override def evalI(using context: Context): D = {
    val resultingEval = this.f.apply(this.source).asInstanceOf[Eval[D]]

    resultingEval.eval
  }
}
