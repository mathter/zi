package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

implicit class PathMapSourceOps[T](val x: Source[PathMap]) {
  def by(path: Path)(implicit ctag: ClassTag[T]): Source[T] = x.dsl.by(x, path)
}
