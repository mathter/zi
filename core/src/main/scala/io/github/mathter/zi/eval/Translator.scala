package io.github.mathter.zi.eval

trait Translator {
  def translate[T](terminal: Terminal): Eval[T]
}
