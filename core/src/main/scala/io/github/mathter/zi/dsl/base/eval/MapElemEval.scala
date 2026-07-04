package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

class MapElemEval[E, D](val listEval: Eval[List[E]], val f: E => D)(implicit dsl: Dsl, tracer: Tracer) extends AbstractEval[List[D]] {
  override def evalI(implicit context: Context): Opt[List[D]] = this.listEval.eval.map(_.map(this.f))
}
