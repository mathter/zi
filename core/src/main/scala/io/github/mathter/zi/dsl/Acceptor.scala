package io.github.mathter.zi.dsl

import io.github.mathter.zi.eval.Terminal

trait Acceptor[T] extends Source[T] {
  def from(source: Source[T]): Source[T] & Terminal
}
