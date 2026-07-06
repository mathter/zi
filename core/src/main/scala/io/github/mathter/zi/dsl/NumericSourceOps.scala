package io.github.mathter.zi.dsl

import scala.reflect.ClassTag

implicit class NumericSourceOps[T](x: Source[T])(using num: Integral[T], classTag: ClassTag[T]) {
  import scala.math.Integral.Implicits.infixIntegralOps

  def +(y: Source[T]): Source[T] = {
    x.composite(y).fun((left, right) => left + right)
  }

  def -(y: Source[T]): Source[T] = {
    x.composite(y).fun((left, right) => left - right)
  }

  def *(y: Source[T]): Source[T] = {
    x.composite(y).fun((left, right) => left * right)
  }

  def /(y: Source[T]): Source[T] = {
    x.composite(y).fun((left, right) => left / right)
  }

  def %(y: Source[T]): Source[T] = {
    x.composite(y).fun((left, right) => left % right)
  }
}