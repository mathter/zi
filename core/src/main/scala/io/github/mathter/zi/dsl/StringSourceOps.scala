package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

implicit class StringSourceOps(private val x: Source[String]) {
  def toUpperCase: Source[String] = x.custom(s => s.toUpperCase)

  inline def toLowerCase: Source[String] = x.custom(s => s.toLowerCase)

  inline def replace(regexpr: String, replacement: String): Source[String] = x.custom(s => s.replaceAll(regexpr, replacement))

  inline def length: Source[Int] = x.custom(_.length)

  inline def isEmpty: Source[Boolean] = x.custom(_.isEmpty)

  inline def isNotEmpty: Source[Boolean] = !this.isEmpty

  inline def matches(regexpr: String): Source[Boolean] = x.custom(_.matches(regexpr))

  inline def trim: Source[String] = x.custom(_.trim)
}
