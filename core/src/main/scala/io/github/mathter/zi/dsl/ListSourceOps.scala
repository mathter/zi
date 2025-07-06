package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

implicit class ListSourceOps[T](val x: Source[List[T]]) {
  def first: Source[T] = x.dsl.first(x)

  def last: Source[T] = x.dsl.last(x)

  def index(source: Source[Int]): Source[T] = x.dsl.index(x, source)

  def mapElem[D](f: Source[T] => Source[D]): Source[List[D]] = x.dsl.mapElem(x, f)

  def group[K](key: Source[T] => Source[K]): Group[K, T] = x.dsl.group(x, key)

  def filter(p: Source[T] => Source[Boolean]): Source[List[T]] = x.dsl.filter(x, p)

  def distinct: Source[List[T]] = x.dsl.distinct(x)
}
