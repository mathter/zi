package io.github.mathter.zi.dsl

trait From[T] {
  def from(source: Source[T]): Source[T]
}
