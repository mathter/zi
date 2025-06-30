package io.github.mathter.zi.dsl

implicit class BooleanOps(x: Source[Boolean]) {
  inline def and(y: Source[Boolean]): Source[Boolean] = x.composite(y).fun((x, y) => x && y)

  inline def or(y: Source[Boolean]): Source[Boolean] = x.composite(y).fun((x, y) => x || y)

  inline def xor(y: Source[Boolean]): Source[Boolean] = x.composite(y).fun((x, y) => x ^ y)

  inline def not: Source[Boolean] = x.custom(!_)

  inline def &&(y: Source[Boolean]): Source[Boolean] = this.and(y)

  inline def ||(y: Source[Boolean]): Source[Boolean] = this.or(y)

  inline def unary_! : Source[Boolean] = this.not

  inline def ^(y: Source[Boolean]): Source[Boolean] = this.xor(y)
}
