package io.github.mathter.zi.processing

trait Serializer[T] {
  def serialize(pathMap: PathMap): T
}
