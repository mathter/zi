package io.github.mathter.zi.conversions

import scala.reflect.ClassTag

trait Conversions {
  def map[T, D](value: T)(implicit classTag: ClassTag[D]): D
}
