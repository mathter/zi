package io.github.mathter.zi.dsl

implicit class BigDecimalSourceOps(x: Source[BigDecimal]) extends NumericSourceOps[BigDecimal] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.bigDecimalSourceOps(x)

  infix inline override def +(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.+(y)

  infix inline override def -(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.-(y)

  infix inline override def *(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.*(y)

  infix inline override def /(y: Source[BigDecimal]): Source[BigDecimal] = this.ops./(y)

  infix inline override def %(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.%(y)
}