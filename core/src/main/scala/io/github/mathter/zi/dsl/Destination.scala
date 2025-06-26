package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

trait Destination extends Source[PathMap] with By[From] {
  def apply[T](path: Path): From[T] = this.by(path)
}
