package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, Evaluator, Terminal}

class DestinationEval(tag: Eval[Any])(implicit dsl: Dsl) extends AbstractDestinationEval {
  override def evalI(implicit context: Context): Opt[PathMap] = Opt(context.destination(this.tag.eval))

  override def from(source: Source[PathMap]): Source[PathMap] & Terminal = new AbstractEval[PathMap] with Terminal {
    override def evalI(implicit context: Context): Opt[PathMap] = {
      Evaluator.eval(source).map(value => {
        context.destination(DestinationEval.this.tag.eval, value)
        value
      }
      )
    }
  }.reg()
}
