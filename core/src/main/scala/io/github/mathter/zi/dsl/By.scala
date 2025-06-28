package io.github.mathter.zi.dsl

import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

trait By[A <: Accessor] {
  def by[T](path: Path)(implicit ctag: ClassTag[T]): A[T]
}
