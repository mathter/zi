package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Acceptor, Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, Terminal}

abstract class AbstractAcceptorEval[T](val acceptor: (Opt[T], Context) => Opt[T])(implicit dsl: Dsl)
  extends AbstractEval[T] with Acceptor[T] {

  override def from(source: Source[T]): Source[T] & Terminal = {
    new AbstractEval[T] with Terminal() {
      override def evalI(implicit context: Context): Opt[T] = {
        acceptor.apply(source.asInstanceOf[Eval[T]].eval, context)
      }
    }
  }
}
