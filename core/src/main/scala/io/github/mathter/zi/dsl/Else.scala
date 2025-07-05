package io.github.mathter.zi.dsl

trait Else[T] extends Source[T], If[T] {
  def If[T](condition: Source[Boolean]): If[T]
}
