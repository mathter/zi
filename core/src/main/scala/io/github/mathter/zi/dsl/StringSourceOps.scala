package io.github.mathter.zi.dsl

object StringSourceOps {
  extension (x: Source[String]) {
    def toUpperCase: Source[String] = x.map(s => s.toUpperCase)

    def toLowerCase: Source[String] = x.map(s => s.toLowerCase)

    def replace(regexpr: String, replacement: String): Source[String] = {
      x.custom(s => s.replaceAll(regexpr, replacement))
    }
  }
}
