package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.{BooleanSource, NumberSource, StringSource}
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Dsl
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.eval.{Context, Eval, Tracer}

import java.lang

class StringSourceEval(private val eval: Eval[String])(implicit dsl: Dsl, tracer: Tracer = Tracer.trace5())
  extends AbstractEval[String] with StringSource {
  override def evalI(using context: Context): Opt[String] = this.eval.eval

  override def toUpperCase: StringSource =
    new StringSourceEval(this.eval) {
      override def evalI(using context: Context): Opt[String] = StringSourceEval.this.eval.eval.map(_.toUpperCase)
    }

  override def toLowerCase: StringSource =
    new StringSourceEval(this.eval) {
      override def evalI(using context: Context): Opt[String] = StringSourceEval.this.eval.eval.map(_.toLowerCase)
    }

  override def replaceAll(regexpr: String, replacement: String): StringSource =
    new StringSourceEval(this.eval) {
      override def evalI(using context: Context): Opt[String] = StringSourceEval.this.eval.eval.map(_.replaceAll(regexpr, replacement))
    }

  override def length(): NumberSource[Integer] = {
    import io.github.mathter.jzi.dsl.base.JavaNumeric.IntegerNumeric

    given numeric: Numeric[Integer] = Numeric[Integer]

    new NumberSourceEval[Integer](null) {
      override def evalI(using context: Context): Opt[Integer] = StringSourceEval.this.eval.eval.map(_.length)
    }
  }

  override def trim(): StringSource =
    new StringSourceEval(this.eval) {
      override def evalI(using context: Context): Opt[String] = StringSourceEval.this.eval.eval.map(_.trim)
    }

  override def isEmpty: BooleanSource =
    new BooleanSourceEval(null) {
      override def evalI(using context: Context): Opt[lang.Boolean] =
        StringSourceEval.this.eval.eval.map(e => e == null || e.isEmpty)
    }

  override def isNotEmpty: BooleanSource =
    new BooleanSourceEval(null) {
      override def evalI(using context: Context): Opt[lang.Boolean] =
        StringSourceEval.this.eval.eval.map(e => e != null && e.nonEmpty)
    }

  override def isBlank: BooleanSource =
    new BooleanSourceEval(null) {
      override def evalI(using context: Context): Opt[lang.Boolean] =
        StringSourceEval.this.eval.eval.map(e => e == null || e.isBlank)
    }

  override def isNotBlank: BooleanSource =
    new BooleanSourceEval(null) {
      override def evalI(using context: Context): Opt[lang.Boolean] =
        StringSourceEval.this.eval.eval.map(e => e == null || !e.isBlank)
    }
}
