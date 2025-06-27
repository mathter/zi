package io.github.mathter.zi.dsl

trait Composite[T, T0] {
  def fun[D](f: (T, T0) => D): Source[D]
}
