package io.github.mathter.zi.dsl

import io.github.mathter.zi.path.Path

trait By[A <: Accessor] {
  def by[T](path: Path): A[T]
}
