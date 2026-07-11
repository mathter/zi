package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.base.JavaNumeric.*
import io.github.mathter.zi.dsl.base.given
import io.github.mathter.jzi.dsl.{BooleanSource, Dsl, ListSource, NumberSource, StringSource}
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.{Source, Dsl as zDsl}
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

import java.math.{BigDecimal, BigInteger}
import java.util.function.Supplier
import java.{lang, util}
import scala.jdk.CollectionConverters

class BaseDsl extends io.github.mathter.zi.dsl.base.BaseDsl with Dsl {
  override def asStringSource(source: Source[String]): StringSource = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new StringSourceEval(null) {
      override def evalI(using context: Context): Opt[String] = source.asInstanceOf[Eval[String]].eval
    }
  }

  override def asByteSource(source: Source[lang.Byte]): NumberSource[lang.Byte] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Byte] = Numeric[lang.Byte]

    new NumberSourceEval[lang.Byte](source.asInstanceOf[Eval[lang.Byte]])
  }

  override def asShortSource(source: Source[lang.Short]): NumberSource[lang.Short] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Short] = Numeric[lang.Short]

    new NumberSourceEval[lang.Short](source.asInstanceOf[Eval[lang.Short]])
  }

  override def asIntSource(source: Source[Integer]): NumberSource[Integer] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Integer] = Numeric[lang.Integer]

    new NumberSourceEval[lang.Integer](source.asInstanceOf[Eval[lang.Integer]])
  }

  override def asLongSource(source: Source[lang.Long]): NumberSource[lang.Long] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Long] = Numeric[lang.Long]

    new NumberSourceEval[lang.Long](source.asInstanceOf[Eval[lang.Long]])
  }

  override def asFloatSource(source: Source[lang.Float]): NumberSource[lang.Float] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Float] = Numeric[lang.Float]

    new NumberSourceEval[lang.Float](source.asInstanceOf[Eval[lang.Float]])
  }

  override def asDoubleSource(source: Source[lang.Double]): NumberSource[lang.Double] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Double] = Numeric[lang.Double]

    new NumberSourceEval[lang.Double](source.asInstanceOf[Eval[lang.Double]])
  }

  override def asBigIntegerSource(source: Source[BigInteger]): NumberSource[BigInteger] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[java.math.BigInteger] = Numeric[java.math.BigInteger]

    new NumberSourceEval[java.math.BigInteger](source.asInstanceOf[Eval[java.math.BigInteger]])
  }

  override def asBigDecimalSource(source: Source[BigDecimal]): NumberSource[BigDecimal] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[java.math.BigDecimal] = Numeric[java.math.BigDecimal]

    new NumberSourceEval[java.math.BigDecimal](source.asInstanceOf[Eval[java.math.BigDecimal]])
  }

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

  override def numberLiteral[T <: Number](supplier: Supplier[T]): NumberSource[T] = {
    val value: T = supplier.get();

    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Integral[T] = (value match {
      case x: lang.Byte => Integral[lang.Byte]
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

  override def literal(value: String): StringSource = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new StringSourceEval(context => Opt(value))
  }

  override def stringLiteral(supplier: Supplier[String]): StringSource = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new StringSourceEval(context => Opt(supplier.get()))
  }

  override def literal(literal: lang.Boolean): BooleanSource = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new BooleanSourceEval(context => Opt(literal))
  }

  override def booleanLiteral(supplier: Supplier[lang.Boolean]): BooleanSource = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new BooleanSourceEval(context => Opt(supplier.get()))
  }

  override def literal[T](literal: util.List[T]): ListSource[T] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    new ListSourceEval[T](dsl.asInstanceOf[zDsl].literal(literal))
  }

  override def first[T](source: Source[util.List[T]]): T = ???
}

object BaseDsl {
  def javaListSource2ListSource[T](x: Source[util.List[T]]): Source[List[T]] = {
    given dsl: zDsl = x.dsl

    new AbstractEval[List[T]] {
      override def evalI(using context: Context): Opt[List[T]] = {
        import scala.jdk.CollectionConverters.given

        x.asInstanceOf[Eval[util.List[T]]].eval.map(e =>
          if (e != null) e.asScala.toList else List()
        )
      }
    }
  }

  def listSource2JavaListSource[T](x: Source[List[T]]): ListSource[T] = {
    given dsl: zDsl = x.dsl

    new ListSourceEval[T](null) {
      override def evalI(using context: Context): Opt[util.List[T]] = {
        import scala.jdk.CollectionConverters.given

        x.eval.map(e =>
          if (e != null) e.asJava else util.List.of()
        )
      }
    }
  }

  given integer2int: Conversion[Source[Integer], Source[Int]] with {
    override def apply(x: Source[Integer]): Source[Int] = {
      given dsl: zDsl = x.dsl

      new AbstractEval[Int]() {
        override def evalI(using context: Context): Opt[Int] = x.eval.map(_.toInt)
      }
    }
  }
}