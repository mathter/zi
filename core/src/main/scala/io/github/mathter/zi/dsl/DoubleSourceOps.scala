package io.github.mathter.zi.dsl

implicit class DoubleSourceOps(x: Source[Double]) extends NumericSourceOps[Double] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.doubleSourceOps(x)

  infix inline override def +(y: Source[Double]): Source[Double] = this.ops.+(y)

  infix inline override def -(y: Source[Double]): Source[Double] = this.ops.-(y)

  infix inline override def *(y: Source[Double]): Source[Double] = this.ops.*(y)

  infix inline override def /(y: Source[Double]): Source[Double] = this.ops./(y)

  infix inline override def %(y: Source[Double]): Source[Double] = this.ops.%(y)
}