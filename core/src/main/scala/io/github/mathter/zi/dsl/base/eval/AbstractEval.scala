package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Composite, Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval}

import scala.reflect.{ClassTag, classTag}

abstract class AbstractEval[T](implicit val dsl: Dsl) extends Eval[T] with Source[T] {
  override def map[D](implicit classTagD: ClassTag[D]): Source[D] = new MapType[T, D](this)

  override def map[D, DS <: Source[D]](f: Source[T] => Source[D]): DS = new MapEval[T, D](this, f).asInstanceOf[DS]

  override def customOpt[D](f: Opt[T] => Opt[D]): Source[D] = new CustomEval[T, D](this, f)

  override def custom[D](f: T => D): Source[D] = new CustomEval[T, D](this, opt => opt.map(f))

  override def composite[T0](source: Source[T0]): Composite[T, T0] = new CompositeEval[T, T0](this, source.asInstanceOf[Eval[T0]])

  override def as[D]: Source[D] = new AbstractEval[D]() {
    override def evalI(implicit context: Context): Opt[D] = AbstractEval.this.eval.asInstanceOf[Opt[D]]
  }

  override def equalsTo(another: Source[T]): Source[Boolean] = new AbstractEval[Boolean]() {
    override def evalI(using context: Context): Opt[Boolean] = {
      AbstractEval.this.eval.flatMap(l => another.asInstanceOf[Eval[T]].eval.map(r => l == r))
    }
  }

  override def eval(implicit context: Context): Opt[T] = {
    this.getCache(context.asInstanceOf[BaseContext])
      .getOrElse({
        val option = this.evalI(context)
        this.putCache(option, context.asInstanceOf[BaseContext])
        option
      })
  }

  def isNothing[X](value: X): Boolean = {
    value.isInstanceOf[Option[?]] && value.asInstanceOf[Option[?]].isEmpty
  }

  def putCache(opt: Opt[T], context: BaseContext): Unit = {
    context.cache.put(this, opt)
  }

  def getCache(context: BaseContext): Option[Opt[T]] = {
    context.cache.get(this).asInstanceOf[Option[Opt[T]]]
  }

  def evalI(context: Context): Opt[T]
}
