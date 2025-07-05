package io.github.mathter.zi.data

import io.github.mathter.zi.path.Path

trait PathMap {
  def apply[T](path: Path): Opt[T]

  def get[T](path: Path): Opt[T] = this.apply(path)

  def iget[T](path: Path): Opt[T]

  def put[T](path: Path, value: T): Unit = update(path, value)

  def update[T](path: Path, value: T): Unit
}

object PathMap {
  def empty: PathMap = {
    EPathMap()
  }
}