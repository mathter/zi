package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.eval.{Context, Eval, Terminal}

object Evaluator {
  def evalSource[T](source: Source[T])(using context: Context): Opt[T] = {
    source.asInstanceOf[Eval[T]].eval
  }

  def eval[T](terminal: Terminal)(using context: Context): Opt[T] = {
    terminal.asInstanceOf[Eval[T]].eval
  }
}
