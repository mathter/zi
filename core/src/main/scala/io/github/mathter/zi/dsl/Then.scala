package io.github.mathter.zi.dsl

trait Then[T] extends Source[T] {
  def Else(source: Source[T]): Else[T]
}
