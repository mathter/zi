package io.github.mathter.zi.dsl

import io.github.mathter.zi.conversions.Conversions
import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

trait Dsl {
  def conversions: Conversions

  def origin: Source[PathMap]

  def destination: Destination

  def destination(source: Source[?]): Destination

  def obj: Destination

  def literal[T](x: T): Source[T]

  def literal[T](f: () => T): Source[T]

  def nothing[T]: Source[T]

  def nil[T]: Source[T]

  def fls: Source[Boolean]

  def tr: Source[Boolean]

  def first[T](source: Source[List[T]]): Source[T]

  def last[T](source: Source[List[T]]): Source[T]

  def index[T](source: Source[List[T]], index: Source[Int]): Source[T]

  def list[T](source: Source[T]): Source[List[T]]

  def unit[T](source: Source[T]): Source[T] = source

  def by[T](source: Source[PathMap], path: Path): Source[T]

  def mapElem[T, D](source: Source[List[T]], f: Source[T] => Source[D]): Source[List[D]]

  def If[T](condition: Source[Boolean]): If[T]

  def group[K, E](source: Source[List[E]], key: Source[E] => Source[K]): Group[K, E]

  def filter[T](source: Source[List[T]], p: Source[T] => Source[Boolean]): Source[List[T]]

  def distinct[T](source: Source[List[T]]): Source[List[T]]
}

given [T](using dsl: Dsl): Conversion[T, Source[T]] with {
  override def apply(x: T): Source[T] = dsl.literal(x)
}