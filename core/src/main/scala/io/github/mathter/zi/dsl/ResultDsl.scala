package io.github.mathter.zi.dsl

trait ResultDsl {
  def result[T]: Acceptor[T]

  def result[T](tag: Source[Any]): Acceptor[T]
}
