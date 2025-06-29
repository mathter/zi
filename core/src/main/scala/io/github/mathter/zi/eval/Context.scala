package io.github.mathter.zi.eval

import io.github.mathter.zi.data.PathMap

trait Context {
  def origin: PathMap

  def destination(tag: Any): PathMap

  def destination(tag:Any, pathMap: PathMap):Unit
}
