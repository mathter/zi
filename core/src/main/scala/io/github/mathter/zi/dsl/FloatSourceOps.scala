package io.github.mathter.zi.dsl

class FloatSourceShadow(x: Source[Float]) extends NumericSourceOps[Float] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.floatSourceOps(x)

  override def +(y: Source[Float]): Source[Float] = this.ops.+(y)

  override def -(y: Source[Float]): Source[Float] = this.ops.-(y)

  override def *(y: Source[Float]): Source[Float] = this.ops.*(y)

  override def /(y: Source[Float]): Source[Float] = this.ops./(y)

  override def %(y: Source[Float]): Source[Float] = this.ops.%(y)
}

implicit class FloatSourceOps(x: Source[Float]) extends FloatSourceShadow(x) {
}