package io.github.mathter.zi.dsl

import scala.reflect.ClassTag

trait Composite3[T, T0, T1, T2, T3] {
  def fun[D](f: (T, T0, T1, T2, T3) => D)(implicit ctag: ClassTag[D]): Source[D]
}
