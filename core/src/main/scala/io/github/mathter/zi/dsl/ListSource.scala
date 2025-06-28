package io.github.mathter.zi.dsl

import scala.reflect.ClassTag

trait ListSource[T] extends Source[List[T]] {
  def etp: ClassTag[T]

  def first: Source[T]

  def last: Source[T]

  def index(index: Source[Int]): Source[T]

  def mapElem[D](f: T => D)(implicit ctag: ClassTag[D]): ListSource[D]
}
