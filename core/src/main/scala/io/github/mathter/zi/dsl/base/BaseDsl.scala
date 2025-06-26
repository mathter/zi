package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.eval.{DestinationEval, ListElementByIndexEval, LiteralEval, NothingEval, OriginSourceEval}
import io.github.mathter.zi.dsl.*
import Dsl.given
import io.github.mathter.zi.eval.Eval
import io.github.mathter.zi.path.Path

class BaseDsl extends Dsl {
  override def origin: Source[PathMap] = new OriginSourceEval()

  override def destination: Destination = this.destination(BaseDsl.DEFAULT_DESTINATION_TAG)

  override def destination(source: Source[?]): Destination = new DestinationEval(source.asInstanceOf[Eval[Any]])

  override def literal[T](x: T): Source[T] = new LiteralEval[T](() => x)

  override def literal[T](f: () => T): Source[T] = new LiteralEval[T](f)

  override def nothing[T]: Source[T] = new NothingEval[T]

  override def first[T](source: Source[List[T]]): Source[T] = new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => 0)

  override def last[T](source: Source[List[T]]): Source[T] = new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => list.length - 1)

  override def asList[T](source: Source[T]): Source[List[T]] = ???

  override def by[T](source: Source[PathMap], path: Path): Source[T] = ???
}

object BaseDsl {
  private val DEFAULT_DESTINATION_TAG: Source[String] = "<<default>>"
}