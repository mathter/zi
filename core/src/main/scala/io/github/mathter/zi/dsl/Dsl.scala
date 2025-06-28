package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

trait Dsl {
  def origin: Source[PathMap]

  def destination: Destination

  def destination(source: Source[?]): Destination

  def literal[T](x: T)(implicit ctag: ClassTag[T]): Source[T]

  def literal[T](f: () => T)(implicit ctag: ClassTag[T]): Source[T]

  def nothing[T](implicit ctag: ClassTag[T]): Source[T]

  def nil[T](implicit ctag: ClassTag[T]): Source[T]

  def first[T](source: Source[List[T]])(implicit ctag: ClassTag[T]): Source[T]

  def last[T](source: Source[List[T]])(implicit ctag: ClassTag[T]): Source[T]

  def asList[T](source: Source[T])(implicit classTag: ClassTag[T]): ListSource[T]

  def unit[T](source: Source[T])(implicit ctag: ClassTag[T]): Source[T] = source

  def by[T](source: Source[PathMap], path: Path)(implicit ctag: ClassTag[T]): Source[T]
}

object Dsl {
  extension [T](x: Source[List[T]]) {
    def first(implicit ctag: ClassTag[T]): Source[T] = x.map(x.dsl.first)

    def last(implicit ctag: ClassTag[T]): Source[T] = x.map(x.dsl.last)

    def mapElem[D](f: Source[T] => Source[D])(implicit ctag: ClassTag[T]): List[Source[D]] = null
  }

  extension [T](x: Source[PathMap]) {
    def by(path: Path)(implicit ctag: ClassTag[T]): Source[T] = x.dsl.by(x, path)
  }
}
