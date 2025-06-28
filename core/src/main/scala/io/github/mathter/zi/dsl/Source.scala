package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.Opt

import scala.reflect.ClassTag

trait Source[T] {
  def dsl: Dsl

  def map[D](using classTagT: ClassTag[T])(using classTagD: ClassTag[D]): Source[D]

  def map[D, DS <: Source[D]](f: Source[T] => Source[D]): DS

  def customOpt[D](f: Opt[T] => Opt[D]): Source[D]

  def custom[D](f: T => D): Source[D]

  def composite[T0](source: Source[T0]): Composite[T, T0]

  def list(implicit ops: Dsl): Source[List[T]] = this.map(ops.list)
}