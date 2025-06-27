package io.github.mathter.zi.`type`

import scala.reflect.ClassTag

class Conversions {
  private val conversions: Map[(ClassTag[?], ClassTag[?]), Conversion[?, ?]] = Map.empty

  def map[T, D](value: T)(using classTagT: ClassTag[T])(using classTagD: ClassTag[D]): D = {
    val a: String = value.asInstanceOf[Any]

    null.asInstanceOf[D]
  }

  given Conversion[Any, String] with {
    override def apply(x: Any): String = if (x == null) null else x.toString
  }
}

object Conversions {
  private val DEFAULT = new Conversions()

  def default: Conversions = DEFAULT
}
