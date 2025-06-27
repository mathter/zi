package io.github.mathter.zi.eval

import io.github.mathter.zi.data.Opt

trait Eval[T] {
  def eval(implicit context: Context): Opt[T]
}
