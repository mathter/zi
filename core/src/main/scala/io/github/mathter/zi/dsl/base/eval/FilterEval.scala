package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

class FilterEval[T](val listEval: Eval[List[T]], p: Source[T] => Source[Boolean])(implicit dsl: Dsl) extends AbstractEval[List[T]] {
  override def evalI(using context: Context): Opt[List[T]] = this.listEval.eval.map(list => list.filter(e =>
    this.p.apply(LiteralEval[T](e)).asInstanceOf[Eval[Boolean]].eval.getOrElse(false)
  ))
}
