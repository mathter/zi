package io.github.mathter.zi.dsl

implicit class StringSourceOps(val x: Source[String]) {
  def toUpperCase: Source[String] = x.custom(s => s.toUpperCase)

  def toLowerCase: Source[String] = x.custom(s => s.toLowerCase)

  def replace(regexpr: String, replacement: String): Source[String] = {
    x.custom(s => s.replaceAll(regexpr, replacement))
  }
}
