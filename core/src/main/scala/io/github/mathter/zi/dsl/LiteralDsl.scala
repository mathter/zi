package io.github.mathter.zi.dsl

trait LiteralDsl {
  def literal[T](x: T): Source[T]

  def literal[T](f: () => T): Source[T]
}
