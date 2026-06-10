package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

class DistinctEval[K, T](val listEval: Eval[List[T]], key: Source[T] => Source[K])(implicit dsl: Dsl) extends AbstractEval[List[T]] {
  private val emptyKey: K = EmptyKey().asInstanceOf[K]

  override def evalI(using context: Context): Opt[List[T]] = {
    this.listEval.eval.map(list => list.distinctBy(e => {
      val originSource = LiteralEval[T](e)
      val keySource = key.apply(originSource)
      keySource.asInstanceOf[Eval[K]].eval.getOrElse(emptyKey)
    }))
  }

  private class EmptyKey {
  }
}