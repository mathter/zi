package io.github.mathter.zi.dsl

class LongSourceShadow(x: Source[Long]) extends NumericSourceOps[Long] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.longSourceOps(x)

  override def +(y: Source[Long]): Source[Long] = this.ops.+(y)

  override def -(y: Source[Long]): Source[Long] = this.ops.-(y)

  override def *(y: Source[Long]): Source[Long] = this.ops.*(y)

  override def /(y: Source[Long]): Source[Long] = this.ops./(y)

  override def %(y: Source[Long]): Source[Long] = this.ops.%(y)
}

implicit class LongSourceOps(x: Source[Long]) extends LongSourceShadow(x) {
}