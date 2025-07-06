package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.conversions.{Conversions, DefaultConversions}
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

class MapType[T, D](val eval: Eval[T])(implicit dsl: Dsl, val ctagD: ClassTag[D]) extends AbstractEval[D] {
  override def evalI(using context: Context): Opt[D] = {
    this.eval.eval
      .map(value => {
        implicit val tagD: ClassTag[D] = ctagD

        this.dsl.conversions.map(value)
      })
  }
}
