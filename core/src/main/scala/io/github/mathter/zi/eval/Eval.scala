package io.github.mathter.zi.eval

trait Eval[T] {
  def eval(implicit context: Context): T
}
