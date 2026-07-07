package io.github.mathter.zi.data.ext

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.path.Path

trait Listener {
  def root: Path

  def related: Set[Path]

  def apply(pathMap: PathMap): Unit
}
