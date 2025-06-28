package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.Context

import scala.reflect.ClassTag

class CalculatedLiteralEval[T](val f: () => T)(implicit dsl: Dsl, ctag: ClassTag[T]) extends AbstractEval[T] {

  override def evalI(using context: Context): Opt[T] = {
    val value: T = this.f()

    Opt(value)
  }
}
