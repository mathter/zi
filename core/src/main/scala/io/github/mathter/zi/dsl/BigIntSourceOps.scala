package io.github.mathter.zi.dsl

class BigIntSourceShadow(x: Source[BigInt]) extends NumericSourceOps[BigInt] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.bigIntSourceOps(x)

  inline override def +(y: Source[BigInt]): Source[BigInt] = this.ops.+(y)

  inline override def -(y: Source[BigInt]): Source[BigInt] = this.ops.-(y)

  inline override def *(y: Source[BigInt]): Source[BigInt] = this.ops.*(y)

  inline override def /(y: Source[BigInt]): Source[BigInt] = this.ops./(y)

  inline override def %(y: Source[BigInt]): Source[BigInt] = this.ops.%(y)
}

implicit class BigIntSourceOps(x: Source[BigInt]) extends BigIntSourceShadow(x) {
}