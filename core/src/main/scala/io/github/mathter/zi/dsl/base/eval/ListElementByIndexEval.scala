package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.eval.{Context, Eval}

class ListElementByIndexEval[T](val listEval: Eval[List[T]], indexFunction: List[T] => Int)(implicit dsl: Dsl) extends AbstractEval[T] {
  override def evalI(using context: Context): Opt[T] = {
    this.listEval.eval
      .map(list => {
        val index = this.indexFunction.apply(list)
        list(index)
      })
  }
}
