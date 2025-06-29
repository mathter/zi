package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.dsl.base.Terminals.cleaner
import io.github.mathter.zi.dsl.base.eval.{Listener, TraceTerminal}
import io.github.mathter.zi.eval.Terminal

import java.lang.ref.Cleaner
import scala.collection.mutable

class Terminals {
  private val terminals = new mutable.Queue[Terminal]

  private val listener = new Listener {
    override def term(terminal: Terminal): Unit = Terminals.this.terminals.addOne(terminal)
  }

  {
    TraceTerminal.reg(this.listener)
    cleaner.register(this, () => {
      TraceTerminal.unreg(this.listener)
    })
  }

  def get: List[Terminal] = this.terminals.toList
}

object Terminals {
  private val cleaner: Cleaner = Cleaner.create()
}
