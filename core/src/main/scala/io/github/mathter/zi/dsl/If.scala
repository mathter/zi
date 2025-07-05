package io.github.mathter.zi.dsl

trait If[T] {
  def Then(source: Source[T]): Then[T]
}
