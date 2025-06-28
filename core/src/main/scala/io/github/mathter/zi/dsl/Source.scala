package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.Opt

import scala.reflect.ClassTag

trait Source[T] {
  def dsl: Dsl

  def tp: ClassTag[T]

  def map[D](using classTagT: ClassTag[T])(using classTagD: ClassTag[D]): Source[D]

  def map[D, DS <: Source[D]](f: Source[T] => Source[D])(implicit ctag: ClassTag[D]): DS

  def customOpt[D](f: Opt[T] => Opt[D])(implicit ctag: ClassTag[D]): Source[D]

  def custom[D](f: T => D)(implicit ctag: ClassTag[D]): Source[D]

  def composite[T0](source: Source[T0]): Composite[T, T0]

  def asList(implicit ops: Dsl, classTag: ClassTag[T]): ListSource[T] = this.map(ops.asList)

  def list[E](implicit ctag: ClassTag[E]): ListSource[E]
}