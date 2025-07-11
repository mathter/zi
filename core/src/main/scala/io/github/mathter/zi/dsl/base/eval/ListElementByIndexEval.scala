package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

class ListElementByIndexEval[T](val listEval: Eval[List[T]], indexEval: Eval[Int])(implicit dsl: Dsl) extends AbstractEval[T] {
  override def evalI(using context: Context): Opt[T] = {
    this.listEval.eval
      .flatMap(list => {
        this.indexEval.eval
          .filter(index => index >= 0 && index < list.length)
          .map(index => list(index))
      })
  }
}
