package io.github.mathter.zi.dsl

class IntSourceShadow(x: Source[Int]) extends NumericSourceOps[Int] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.intSourceOps(x)

  override def +(y: Source[Int]): Source[Int] = this.ops.+(y)

  override def -(y: Source[Int]): Source[Int] = this.ops.-(y)

  override def *(y: Source[Int]): Source[Int] = this.ops.*(y)

  override def /(y: Source[Int]): Source[Int] = this.ops./(y)

  override def %(y: Source[Int]): Source[Int] = this.ops.%(y)
}

implicit class IntSourceOps(x: Source[Int]) extends IntSourceShadow(x) {
}