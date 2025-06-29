package io.github.mathter.zi.dsl

trait NumericSourceOps[T] {
  def +(y: Source[T]): Source[T]

  def -(y: Source[T]): Source[T]

  def *(y: Source[T]): Source[T]

  def /(y: Source[T]): Source[T]

  def %(y: Source[T]): Source[T]
}
