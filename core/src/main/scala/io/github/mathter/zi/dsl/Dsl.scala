package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

import scala.language.implicitConversions

trait Dsl {
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

  def list[T](source: Source[T]): Source[List[T]]

  def unit[T](source: Source[T]): Source[T] = source

  def by[T](source: Source[PathMap], path: Path): Source[T]

  def mapElem[T, D](source: Source[List[T]], f: Source[T] => Source[D]): Source[List[D]]

  def If[T](condition: Source[Boolean]): If[T]
}