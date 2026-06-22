package io.github.mathter.zi.eval

case class EvalException(val tracer: Tracer, throwable: Throwable) extends RuntimeException(throwable) {
  def this(tracer: Tracer) = {
    this(tracer, null)
  }

  override def getMessage: String = {
    super.getMessage + '\n' + this.tracer.toString
  }
}
