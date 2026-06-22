package io.github.mathter.zi.dsl

implicit class DoubleSourceShadow(x: Source[Double]) extends NumericSourceOps[Double] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.doubleSourceOps(x)

  override def +(y: Source[Double]): Source[Double] = this.ops.+(y)

  override def -(y: Source[Double]): Source[Double] = this.ops.-(y)

  override def *(y: Source[Double]): Source[Double] = this.ops.*(y)

  override def /(y: Source[Double]): Source[Double] = this.ops./(y)

  override def %(y: Source[Double]): Source[Double] = this.ops.%(y)
}

implicit class DoubleSourceOps(x: Source[Double]) extends DoubleSourceShadow(x) {
}