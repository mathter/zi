package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.path.Path

trait Dsl {
  def origin: Source[PathMap]

  def destination: Destination

  def destination(source: Source[?]): Destination

  def literal[T](x: T): Source[T]

  def literal[T](f: () => T): Source[T]

  def nothing[T]: Source[T]

  def first[T](source: Source[List[T]]): Source[T]

  def last[T](source: Source[List[T]]): Source[T]

  def asList[T](source: Source[T]): Source[List[T]]

  def unit[T](source: Source[T]): Source[T] = source

  def by[T](source: Source[PathMap], path: Path): Source[T]
}

object Dsl {
  private val dsl = new BaseDsl

  given [T]: Conversion[T, Source[T]] with {
    override def apply(x: T): Source[T] = dsl.literal(x)
  }

  extension [T](x: Source[List[T]]) {
    def first: Source[T] = x.map(dsl.first)
  }

  extension [T](x: Source[List[T]]) {
    def last: Source[T] = x.map(dsl.last)
  }

  extension [T](x: Source[List[T]]) {
    def mapElem[D](f: Source[T] => Source[D]): List[Source[D]] = null
  }

  extension [T](x: Source[PathMap]) {
    def by(path: Path): Source[T] = dsl.by(x, path)
  }
}
