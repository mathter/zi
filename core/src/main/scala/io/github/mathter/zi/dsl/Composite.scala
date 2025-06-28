package io.github.mathter.zi.dsl

import scala.reflect.ClassTag

trait Composite[T, T0] {
  def fun[D](f: (T, T0) => D)(implicit ctag: ClassTag[D]): Source[D]
}
