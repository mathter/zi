package io.github.mathter.zi.dsl

implicit class LongSourceOps(x: Source[Long]) extends NumericSourceOps[Long] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.longSourceOps(x)

  infix inline override def +(y: Source[Long]): Source[Long] = this.ops.+(y)

  infix inline override def -(y: Source[Long]): Source[Long] = this.ops.-(y)

  infix inline override def *(y: Source[Long]): Source[Long] = this.ops.*(y)

  infix inline override def /(y: Source[Long]): Source[Long] = this.ops./(y)

  infix inline override def %(y: Source[Long]): Source[Long] = this.ops.%(y)
}