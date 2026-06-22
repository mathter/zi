package io.github.mathter.zi.eval

import scala.collection.mutable

class Tracer(private val parent: Tracer,
             private val stackTraceElement: StackTraceElement,
             private val level: Int
            ) {
  override def toString: String = {
    val sb = new StringBuilder()
    sb.append("\tparent=").append(this.parent).append("\n")
    sb.append("\tstacktrace=").append(this.stackTraceElement).append("\n")
    sb.append("\tlevel=").append(this.level).append("\n")

    sb.toString()
  }
}

object Tracer {
  private val local = new ThreadLocal[mutable.Stack[Tracer]]

  local.set(mutable.Stack())

  def trace(): Tracer = {
    val stackTrace = Thread.currentThread().getStackTrace
    val stack = local.get()
    var prev = if (stack.isEmpty) null else stack.top

    while (prev != null && prev.level >= stackTrace.length) {
      stack.pop()
      prev = if (stack.isEmpty) null else stack.top
    }

    val tracer = new Tracer(prev, stackTrace(3), stackTrace.length)
    stack.push(tracer)
    tracer
  }
}
