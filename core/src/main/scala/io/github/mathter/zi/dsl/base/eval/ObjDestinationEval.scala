package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Evaluator, Terminal}
import io.github.mathter.zi.path.Path

class ObjDestinationEval(implicit dsl: Dsl) extends AbstractDestinationEval {
  override def evalI(implicit context: Context): Opt[PathMap] = {
    context.asInstanceOf[BaseContext].cache.getOrElseUpdate(this, Opt(PathMap.empty)).asInstanceOf[Opt[PathMap]]
  }

  override def from(source: Source[PathMap]): Source[PathMap] & Terminal = new AbstractEval[PathMap] with Terminal {
    override def evalI(implicit context: Context): Opt[PathMap] = {
      Evaluator.eval(source).map(value => {
        context.asInstanceOf[BaseContext].cache.put(this, Opt(value))
        value
      })
    }
  }
}
