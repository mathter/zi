package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.eval.{Eval, Terminal}

class Translator extends io.github.mathter.zi.eval.Translator {
  override def translate[T](terminal: Terminal): Eval[T] = terminal.asInstanceOf[Eval[T]]
}
