package io.github.mathter.zi.dsl

class BigDecimalSourceShadow(x: Source[BigDecimal]) extends NumericSourceOps[BigDecimal] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.bigDecimalSourceOps(x)

  inline override def +(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.+(y)

  inline override def -(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.-(y)

  inline override def *(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.*(y)

  inline override def /(y: Source[BigDecimal]): Source[BigDecimal] = this.ops./(y)

  inline override def %(y: Source[BigDecimal]): Source[BigDecimal] = this.ops.%(y)
}

implicit class BigDecimalSourceOps(x: Source[BigDecimal]) extends BigDecimalSourceShadow(x) {
}