package io.github.mathter.zi.dsl

import scala.reflect.ClassTag

implicit class ListSourceOps[E](val x: Source[List[E]]) {
  def first(implicit ctag: ClassTag[E]): Source[E] = x.map(x.dsl.first)

  def last(implicit ctag: ClassTag[E]): Source[E] = x.map(x.dsl.last)

  def mapElem[D](f: Source[E] => Source[D])(implicit ctag: ClassTag[E]): Source[List[D]] = x.dsl.mapElem(f)
}
