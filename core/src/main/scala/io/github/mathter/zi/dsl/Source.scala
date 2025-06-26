package io.github.mathter.zi.dsl

trait Source[T] {
  def map[D](f: Source[T] => Source[D]): Source[D]

  def customMap[D](f: T => D): Source[D]

  def customWithNothing[D <: Any | Option[Nothing]](f: T => D): D

  def asList(implicit ops: Dsl): Source[List[T]] = this.map(ops.asList)
}