package io.github.mathter.zi.processing

import io.github.mathter.zi.data.PathMap

trait Serializer[T] {
  def serialize(pathMap: PathMap): T
}
