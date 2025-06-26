package io.github.mathter.zi.dsl

import io.github.mathter.zi.eval.Terminal

trait From[T] extends Accessor[T] {
  def from(source: Source[T]): Source[T] & Terminal
}
