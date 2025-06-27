package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.Opt

import scala.reflect.ClassTag

trait Source[T] {
  def map[D](using classTagT: ClassTag[T])(using classTagD: ClassTag[D]): Source[D]

  def map[D](f: Source[T] => Source[D]): Source[D]

  def customOpt[D](f: Opt[T] => Opt[D]): Source[D]

  def custom[D](f: T => D): Source[D]

  def composite[T0](source: Source[T0]): Composite[T, T0]

  def asList(implicit ops: Dsl): Source[List[T]] = this.map(ops.asList.asInstanceOf[Source[T] => Source[List[T]]])
}