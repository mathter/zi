package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

class ResultEval[T](val tag: Eval[Any])(implicit dsl: Dsl, tracer: Tracer) extends AbstractAcceptorEval[T](
  (opt, context) => {
    tag.eval(using context).flatMap(e => context.target(e, opt))
  }
) {
  override def evalI(implicit context: Context): Opt[T] = {
    tag.eval.flatMap(e => context.target(e))
  }
}