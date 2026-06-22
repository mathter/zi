package io.github.mathter.zi.dsl

trait NumericSourceOps[T] {
  infix def +(y: Source[T]): Source[T]

  infix def -(y: Source[T]): Source[T]

  infix def *(y: Source[T]): Source[T]

  infix def /(y: Source[T]): Source[T]

  infix def %(y: Source[T]): Source[T]
}
