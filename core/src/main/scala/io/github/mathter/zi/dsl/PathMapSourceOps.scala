package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.path.Path

class PathMapSourceShadow(val x: Source[PathMap]) {
  def by[T](path: Path): Source[T] = x.dsl.by(x, path)
}

implicit class PathMapSourceOps(x: Source[PathMap]) extends PathMapSourceShadow(x) {
}