package io.github.mathter.zi.eval

import io.github.mathter.zi.dsl.Source

object Evaluator {
  def eval[T](eval: Eval[T])(using context: Context): T = {
    eval.eval
  }

  def eval[T](source: Source[T])(using context: Context): T = {
    source.asInstanceOf[Eval[T]].eval
  }

  def eval[T](terminal: Terminal)(using context: Context): T = {
    terminal.asInstanceOf[Eval[T]].eval
  }
}
