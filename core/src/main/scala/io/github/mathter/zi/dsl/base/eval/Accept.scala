package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.eval.Context

trait Accept[T] {
  def apply(opt: Opt[T], context: Context): Opt[T]
}
