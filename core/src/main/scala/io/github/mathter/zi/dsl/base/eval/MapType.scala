package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.conversions.{Conversions, DefaultConversions}
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

class MapType[T, D](val ctagT: ClassTag[T], implicit val ctagD: ClassTag[D], val eval: Eval[T])(implicit dsl: Dsl) extends AbstractEval[D] {
  override def evalI(using context: Context): Opt[D] = {
    this.eval.eval
      .map(value => {
        implicit val tagT: ClassTag[T] = ctagT
        implicit val tagD: ClassTag[D] = ctagD

        DefaultConversions.default.map(value)
      })
  }
}
