package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.eval.{Context, Eval}

class ListElementByIndexEval[T](val listEval: Eval[List[T]], indexFunction: List[T] => Int) extends AbstractEval[T] {
  override def evalI(context: Context): T = {
    val list = this.listEval.eval(context).asInstanceOf[Any]

    list match {
      case nothing: Option[Nothing] => None.asInstanceOf[T]
      case list: List[T] => {
        val index = this.indexFunction.apply(list)
        list(index)
      }
    }
  }
}
