package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.dsl.{Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

abstract class AbstractEval[T] extends Eval[T] with Source[T] {
  override def map[D](f: Source[T] => Source[D]): Source[D] = new MapEval[T, D](this, f)

  override def customMap[D](f: T => D): Source[D] = new CustomEval[T, D](this, f)

  override def customWithNothing[D <: Any | Option[Nothing]](f: T => D): D = new CustomWithNothingEval[T, D](this, f)

  override def eval(implicit context: Context): T = {
    this.evalI(context)
  }

  def isNothing[X](value: X): Boolean = {
    value.isInstanceOf[Option[?]] && value.asInstanceOf[Option[?]].isEmpty
  }

  def evalI(context: Context): T
}
