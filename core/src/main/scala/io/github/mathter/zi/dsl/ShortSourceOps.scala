package io.github.mathter.zi.dsl

implicit class ShortSourceOps(x: Source[Short]) extends NumericSourceOps[Short] {
  implicit private val dsl: Dsl = x.dsl

  private val ops = this.dsl.shortSourceOps(x)

  infix inline override def +(y: Source[Short]): Source[Short] = this.ops.+(y)

  infix inline override def -(y: Source[Short]): Source[Short] = this.ops.-(y)

  infix inline override def *(y: Source[Short]): Source[Short] = this.ops.*(y)

  infix inline override def /(y: Source[Short]): Source[Short] = this.ops./(y)

  infix inline override def %(y: Source[Short]): Source[Short] = this.ops.%(y)
}