package io.github.mathter.zi.dsl

implicit class BigIntSourceOps(x: Source[BigInt]) extends NumericSourceOps[BigInt] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.bigIntSourceOps(x)

  override def +(y: Source[BigInt]): Source[BigInt] = this.ops.+(y)

  override def -(y: Source[BigInt]): Source[BigInt] = this.ops.-(y)

  override def *(y: Source[BigInt]): Source[BigInt] = this.ops.*(y)

  override def /(y: Source[BigInt]): Source[BigInt] = this.ops./(y)

  override def %(y: Source[BigInt]): Source[BigInt] = this.ops.%(y)
}
