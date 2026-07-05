package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

class ListSourceShadow[T](val x: Source[List[T]]) {
  inline def first: Source[T] = x.dsl.first(x)

  inline def last: Source[T] = x.dsl.last(x)

  infix inline def index(source: Source[Int]): Source[T] = x.dsl.index(x, source)

  infix inline def mapElem[D](f: T => D): Source[List[D]] = x.dsl.mapElem(x, f)

  infix inline def mapsElem[D](f: Source[T] => Source[D]): Source[List[D]] = x.dsl.mapsElem(x, f)

  infix inline def group[K](key: Source[T] => Source[K]): Group[K, T] = x.dsl.group(x, key)

  infix inline def filter(p: Source[T] => Source[Boolean]): Source[List[T]] = x.dsl.filter(x, p)

  inline def distinct: Source[List[T]] = this.distinctBy(e => e)

  infix inline def distinctBy[K](key: Source[T] => Source[K]): Source[List[T]] = x.dsl.distinctBy(x, key)
}

implicit class ListSourceOps[T](x: Source[List[T]]) extends ListSourceShadow(x) {
}