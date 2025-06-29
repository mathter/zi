package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

trait Destination extends Source[PathMap] with By[From] with From[PathMap] {
  def apply[T](path: Path)(implicit ctag: ClassTag[T]): From[T] = this.by(path)
}
