package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.`type`.Conversions
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

class MapType[T, D](val classTagT: ClassTag[T], val classTagD: ClassTag[D], val eval: Eval[T]) extends AbstractEval[D] {
  override def evalI(using context: Context): Opt[D] = {
    this.eval.eval
      .map(value => {
        implicit val tagT: ClassTag[T] = classTagT
        implicit val tagD: ClassTag[D] = classTagD

        Conversions.default.map(value)
      })
  }
}
