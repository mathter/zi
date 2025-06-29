package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.dsl.base.eval.TraceTerminal.put
import io.github.mathter.zi.eval.Terminal

import scala.collection.mutable

implicit class TraceTerminal[T <: Terminal](x: T) {
  def reg(): T = {
    put(x)
    x
  }
}

object TraceTerminal {
  protected val listeners: ThreadLocal[mutable.Set[Listener]] = new ThreadLocal[mutable.Set[Listener]]

  protected def put(terminal: Terminal): Unit = {
    val listeners = this.listeners.get

    if (listeners != null) {
      listeners.foreach(_.term(terminal))
    }
  }

  def reg(listener: Listener): Unit = {
    if (listener != null) {
      var listeners = this.listeners.get

      if (listeners == null) {
        listeners = mutable.Set.empty
        this.listeners.set(listeners)
      }

      listeners.addOne(listener)
    }
  }

  def unreg(listener: Listener): Unit = {
    if (listener != null) {
      val listeners: mutable.Set[Listener] = this.listeners.get

      if (listeners != null) {
        listeners.remove(listener)

        if (listeners.isEmpty) {
          this.listeners.set(null)
        }
      }
    }
  }
}

trait Listener {
  def term(terminal: Terminal): Unit = {
  }
}
