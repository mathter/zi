package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Composite, Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.ClassTag

abstract class AbstractEval[T](implicit val dsl: Dsl) extends Eval[T] with Source[T] {
  override def map[D](using classTagT: ClassTag[T])(using classTagD: ClassTag[D]): Source[D] = new MapType[T, D](classTagT, classTagD, this)

  override def map[D, DS <: Source[D]](f: Source[T] => Source[D])(implicit ctag: ClassTag[D]): DS = new MapEval[T, D](this, f).asInstanceOf[DS]

  override def customOpt[D](f: Opt[T] => Opt[D])(implicit ctag: ClassTag[D]): Source[D] = new CustomEval[T, D](this, f)

  override def custom[D](f: T => D)(implicit ctag: ClassTag[D]): Source[D] = new CustomEval[T, D](this, opt => opt.map(f))

  override def composite[T0](source: Source[T0]): Composite[T, T0] = new CompositeEval[T, T0](this, source.asInstanceOf[Eval[T0]])

  override def eval(implicit context: Context): Opt[T] = {
    val option = this.evalI(context)

    option
  }

  def isNothing[X](value: X): Boolean = {
    value.isInstanceOf[Option[?]] && value.asInstanceOf[Option[?]].isEmpty
  }

  def evalI(context: Context): Opt[T]
}
