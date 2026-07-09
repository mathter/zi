package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.base.JavaNumeric.*
import io.github.mathter.jzi.dsl.{Dsl, NumberSource}
import io.github.mathter.zi.eval.{Eval, Tracer}

import java.lang

class BaseDsl extends io.github.mathter.zi.dsl.base.BaseDsl with Dsl {
  override def literal(literal: lang.Byte): NumberSource[lang.Byte] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Byte] = Numeric[lang.Byte]

    new NumberSourceEval[lang.Byte](this.literal(literal.asInstanceOf[Byte]).asInstanceOf[Eval[lang.Byte]])
  }

  override def literal(literal: lang.Short): NumberSource[lang.Short] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[Short] = Numeric[Short]

    new NumberSourceEval[lang.Short](this.literal(literal.asInstanceOf[Short]).asInstanceOf[Eval[lang.Short]])
  }

  override def literal(literal: Integer): NumberSource[Integer] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[Integer] = Numeric[Integer]

    new NumberSourceEval[Integer](this.literal(literal.asInstanceOf[Int]).asInstanceOf[Eval[Integer]])
  }

  override def literal(literal: lang.Long): NumberSource[lang.Long] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Long] = Numeric[lang.Long]

    new NumberSourceEval[lang.Long](this.literal(literal.asInstanceOf[Long]).asInstanceOf[Eval[lang.Long]])
  }

  override def literal(literal: lang.Float): NumberSource[lang.Float] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Float] = Numeric[lang.Float]

    new NumberSourceEval[lang.Float](this.literal(literal.asInstanceOf[Float]).asInstanceOf[Eval[lang.Float]])
  }

  override def literal(literal: lang.Double): NumberSource[lang.Double] = {
    given tracer: Tracer = Tracer.trace3()

    given dsl: BaseDsl = this

    given num: Numeric[lang.Double] = Numeric[lang.Double]

    new NumberSourceEval[lang.Double](this.literal(literal.asInstanceOf[Double]).asInstanceOf[Eval[lang.Double]])
  }
}