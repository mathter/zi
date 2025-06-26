package io.github.mathter.zi.exec

import io.github.mathter.zi.dsl.Destination

trait Eval {
  def eval(destination: Destination)(implicit context: Context): Unit
}
