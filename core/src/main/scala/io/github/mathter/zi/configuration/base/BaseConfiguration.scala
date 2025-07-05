package io.github.mathter.zi.configuration.base

import io.github.mathter.zi.configuration.Configuration
import io.github.mathter.zi.dsl.Source
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.eval.Terminal

class BaseConfiguration extends BaseDsl with Configuration {
  def apply(f: BaseConfiguration => Unit): List[Terminal] = {
    val terminals = this.terminals
    f.apply(this)
    terminals.get
  }
}
