package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Composite, Dsl, Source}
import io.github.mathter.zi.eval.{Context, Eval, EvalException, Tracer}

import scala.reflect.ClassTag

abstract class AbstractEval[T](implicit val dsl: Dsl, tracer: Tracer) extends Eval[T] with Source[T] {
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
    if (this.pure) {
      this.cacheGetOrElseUpdate(
        {
          try {
            this.evalI(context)
          } catch {
            case e: EvalException => throw e
            case e: Exception => throw EvalException(this.tracer, e)
          }
        },
        context
      )
    } else {
      try {
        this.evalI(context)
      } catch {
        case e: EvalException => throw e
        case e: Exception => throw EvalException(this.tracer, e)
      }
    }
  }

  def cachePut[T](opt: Opt[T], context: Context): Unit = {
    context.asInstanceOf[BaseContext].cache.put(this, opt)
  }

  def cacheGet[T](context: Context): Opt[T] = {
    context.asInstanceOf[BaseContext].cache.get(this).map(e => Opt(e.asInstanceOf[T])).getOrElse(Opt.empty)
  }

  def cacheGetOrElseUpdate[T](opt: Opt[T], context: Context): Opt[T] = {
    context.asInstanceOf[BaseContext].cache.getOrElseUpdate(this, opt).asInstanceOf[Opt[T]]
  }

  override def pure: Boolean = false

  override def pure(pure: Boolean): Source[T] = this

  def evalI(context: Context): Opt[T]
}