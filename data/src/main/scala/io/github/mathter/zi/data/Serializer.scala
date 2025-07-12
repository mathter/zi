package io.github.mathter.zi.data

trait Serializer[T] {
  def serialize(pathMap: PathMap): T
}
