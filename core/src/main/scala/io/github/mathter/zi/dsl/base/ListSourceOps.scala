package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.dsl.base.BaseDsl

final implicit class ListSourceOps[T](val x: Source[List[T]])(using dsl: BaseDsl) extends io.github.mathter.zi.dsl.ListSourceOps[T] {
  override def first: Source[T] = dsl.first(x)

  override def last: Source[T] = dsl.last(x)

  override def index(source: Source[Int]): Source[T] = dsl.index(x, source)

  override def mapElem[D](f: Source[T] => Source[D]): Source[List[D]] = dsl.mapElem(x, f)
}
