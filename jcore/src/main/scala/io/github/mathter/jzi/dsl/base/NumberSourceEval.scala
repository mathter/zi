package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.NumberSource
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

import scala.math.Integral.Implicits.infixIntegralOps

class NumberSourceEval[T <: Number]
(val eval: Eval[T])(using dsl: Dsl, tracer: Tracer = Tracer.trace5(), num: Integral[T])
  extends AbstractEval[T]
    with NumberSource[T] {
  override def evalI(using context: Context): Opt[T] = eval.eval

  override def plus(source: NumberSource[T]): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.flatMap(left => source.asInstanceOf[Eval[T]].eval.map(right => left + right))
    }

  override def minus(source: NumberSource[T]): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.flatMap(left => source.asInstanceOf[Eval[T]].eval.map(right => left - right))
    }

  override def multiply(source: NumberSource[T]): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.flatMap(left => source.asInstanceOf[Eval[T]].eval.map(right => left * right))
    }

  override def divide(source: NumberSource[T]): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.flatMap(left => source.asInstanceOf[Eval[T]].eval.map(right => left / right))
    }

  override def rem(source: NumberSource[T]): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.flatMap(left => source.asInstanceOf[Eval[T]].eval.map(right => left % right))
    }

  override def abs(): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.map(_.abs)
    }

  override def negate(): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.map(-_)
    }

  override def sign(): NumberSource[T] =
    new NumberSourceEval[T](this.eval) {
      override def evalI(using context: Context): Opt[T] =
        this.eval.eval.map(_.sign)
    }
}
