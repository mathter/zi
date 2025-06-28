package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

class MapElemEval[E, D](val listEval: Eval[List[E]], val f: Source[E] => Source[D])(implicit dsl: Dsl) extends AbstractEval[List[D]] {
  override def evalI(implicit context: Context): Opt[List[D]] = {
    this.listEval.eval.map(_.map(elem => new AbstractEval[E] {
        override def evalI(context: Context): Opt[E] = Opt(elem)
      })
      .map(this.f)
      .flatMap(_.asInstanceOf[Eval[D]].eval)
    )
  }
}
