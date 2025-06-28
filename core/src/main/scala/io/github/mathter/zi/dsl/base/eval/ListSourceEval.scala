package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

class ListSourceEval[E](val eval: Eval[?])(implicit dsl: Dsl, ctag: ClassTag[E]) extends AstractListSourceEval[E] {
  override def evalI(implicit context: Context): Opt[List[E]] =
    this.eval.eval.map(e => e match {
      case list: List[E] => list
      case _ => List(e.asInstanceOf[E])
    })
}
