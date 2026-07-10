package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.base.JavaNumeric.*
import io.github.mathter.jzi.dsl.{Dsl, NumberSource}
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

import java.lang
import java.math.BigInteger
import java.math.BigDecimal
import java.util.function.Supplier

class BaseDsl extends io.github.mathter.zi.dsl.base.BaseDsl with Dsl {
  override def literal(literal: lang.Byte): NumberSource[lang.Byte] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Byte] = Numeric[lang.Byte]

    new NumberSourceEval[lang.Byte]((context: Context) => Opt(literal))
  }

  override def literal(literal: lang.Short): NumberSource[lang.Short] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[Short] = Numeric[Short]

    new NumberSourceEval[lang.Short]((context: Context) => Opt(literal))
  }

  override def literal(literal: Integer): NumberSource[Integer] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[Integer] = Numeric[Integer]

    new NumberSourceEval[Integer]((context: Context) => Opt(literal))
  }

  override def literal(literal: lang.Long): NumberSource[lang.Long] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Long] = Numeric[lang.Long]

    new NumberSourceEval[lang.Long]((context: Context) => Opt(literal))
  }

  override def literal(literal: lang.Float): NumberSource[lang.Float] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Float] = Numeric[lang.Float]

    new NumberSourceEval[lang.Float]((context: Context) => Opt(literal))
  }

  override def literal(literal: lang.Double): NumberSource[lang.Double] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Double] = Numeric[lang.Double]

    new NumberSourceEval[lang.Double]((context: Context) => Opt(literal))
  }

  override def literal(literal: BigInteger): NumberSource[BigInteger] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[BigInteger] = Numeric[BigInteger]

    new NumberSourceEval[BigInteger]((context: Context) => Opt(literal))
  }

  override def literal(literal: BigDecimal): NumberSource[BigDecimal] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[BigDecimal] = Numeric[BigDecimal]

    new NumberSourceEval[BigDecimal]((context: Context) => Opt(literal))
  }

  override def literal[T <: Number](supplier: Supplier[T]): NumberSource[T] = {
    val value: T = supplier.get();

    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Integral[T] = (value match {
      case x: Byte => Integral[lang.Byte]
      case x: lang.Short => Integral[lang.Short]
      case x: lang.Integer => Integral[lang.Integer]
      case x: lang.Long => Integral[lang.Long]
      case x: lang.Float => Integral[lang.Float]
      case x: lang.Double => Integral[lang.Double]
      case x: java.math.BigInteger => Integral[java.math.BigInteger]
      case x: java.math.BigDecimal => Integral[java.math.BigDecimal]
    }).asInstanceOf[Integral[T]]

    new NumberSourceEval[T](context => Opt(value))
  }
}