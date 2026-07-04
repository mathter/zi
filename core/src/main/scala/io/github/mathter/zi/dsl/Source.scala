package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.Opt

import javax.lang.model.SourceVersion
import scala.reflect.ClassTag

trait Source[T] {
  def dsl: Dsl

  def maps[D, DS <: Source[D]](f: Source[T] => Source[D]): DS

  def map[D, DS <: Source[D]](f: T => D): DS

  infix def customOpt[D](f: Opt[T] => Opt[D]): Source[D]

  infix def custom[D](f: T => D): Source[D]

  infix def composite[T0](source: Source[T0]): Composite[T, T0]

  def list: Source[List[T]] = this.dsl.list(this)

  def as[D]: Source[D]

  infix def equalsTo(another: Source[T]): Source[Boolean]

  def ==(another: Source[T]): Source[Boolean] = this.equalsTo(another)

  def pure: Boolean

  def pure(pure: Boolean): Source[T]
}