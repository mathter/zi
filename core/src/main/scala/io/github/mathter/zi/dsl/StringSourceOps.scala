package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

class StringSourceShadow(private val x: Source[String]) {
  def toUpperCase: Source[String] = x.custom(s => s.toUpperCase)

  def toLowerCase: Source[String] = x.custom(s => s.toLowerCase)

  def replace(regexpr: String, replacement: String): Source[String] =
    x.custom(s => s.replaceAll(regexpr, replacement))

  def length: Source[Int] = x.custom(_.length)

  def isEmpty: Source[Boolean] = x.custom(_.isEmpty)

  def matches(regexpr: String): Source[Boolean] = x.custom(_.matches(regexpr))

  def trim: Source[String] = x.custom(_.trim)
}

implicit class StringSourceOps(private val x: Source[String]) extends StringSourceShadow(x) {
}
