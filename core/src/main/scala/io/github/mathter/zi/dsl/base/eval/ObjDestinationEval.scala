package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, Evaluator, Terminal}

class ObjDestinationEval(implicit dsl: Dsl) extends AbstractDestinationEval {
  override def evalI(implicit context: Context): Opt[PathMap] = {
    val container: Container = context.asInstanceOf[BaseContext].cache.getOrElseUpdate(this, Container(PathMap.empty)).asInstanceOf[Container]
    Opt(container.wrapped)
  }

  override def from(source: Source[PathMap]): Source[PathMap] & Terminal = new AbstractEval[PathMap] with Terminal {
    override def evalI(implicit context: Context): Opt[PathMap] = {
      Evaluator.eval(source).map(value => {
        context.asInstanceOf[BaseContext].cache.put(ObjDestinationEval.this, Container(value))
        value
      })
    }
  }

  case class Container(wrapped: PathMap) {
  }
}
