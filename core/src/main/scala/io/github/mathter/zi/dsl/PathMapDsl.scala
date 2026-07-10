package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

trait PathMapDsl {
  def obj: Acceptor[PathMap]

  def by[T](source: Source[PathMap], path: Path): Source[T]

  def by[T](source: Acceptor[PathMap], path: Path): Acceptor[T]
}
