package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Dsl, ListSource, Source}
import io.github.mathter.zi.eval.{Context, Eval, Evaluator}

import scala.reflect.{ClassTag, classTag}

abstract class AstractListSourceEval[E](implicit dsl: Dsl, val etp: ClassTag[E])
  extends AbstractEval[List[E]](using dsl, classTag[List[E]]), ListSource[E] {
  outer =>

  override def first: Source[E] = new AbstractEval[E]() {
    override def evalI(implicit context: Context): Opt[E] =
      outer.eval.filter(_.nonEmpty).map(_.head)
  }

  override def last: Source[E] = new AbstractEval[E]() {
    override def evalI(implicit context: Context): Opt[E] =
      outer.eval.filter(_.nonEmpty).map(_.last)
  }

  override def index(index: Source[Int]): Source[E] = new AbstractEval[E]() {
    override def evalI(implicit context: Context): Opt[E] = {
      Evaluator.eval(index).flatMap(indx => outer.eval.filter(list => list.length < indx + 1).map(_(indx)))
    }
  }

  override def mapElem[D](f: E => D)(implicit ctag: ClassTag[D]): ListSource[D] = new ListSourceEval[D](
    new AbstractEval[List[D]]() {
      override def evalI(implicit context: Context): Opt[List[D]] = {
        outer.eval.map(_.map(f))
      }
    }
  )
}
