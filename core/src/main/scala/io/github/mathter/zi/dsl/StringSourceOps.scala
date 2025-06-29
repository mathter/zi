package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

implicit class StringSourceOps(private val x: Source[String]) extends AnyVal {
  def toUpperCase: Source[String] = x.custom(s => s.toUpperCase)

  def toLowerCase: Source[String] = x.custom(s => s.toLowerCase)

  def replace(regexpr: String, replacement: String): Source[String] = {
    x.custom(s => s.replaceAll(regexpr, replacement))
  }
}
