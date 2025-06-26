package io.github.mathter.zi.mem

import io.github.mathter.zi.path.Path

trait PathMap {
  def apply[T](path: Path): T

  def get[T](path: Path): T = this.apply(path)

  def put[T](path: Path, value: T): Unit = update(path, value)

  def update[T](path: Path, value: T): Unit
}

object PathMap {
  def apply(): PathMap = {
    EPathMap()
  }
}