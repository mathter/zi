package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.*
import io.github.mathter.zi.dsl.base.eval.*
import io.github.mathter.zi.eval.Eval
import io.github.mathter.zi.path.Path

class BaseDsl extends Dsl {
  implicit val dsl: Dsl = this

  override def origin: Source[PathMap] =
    new OriginSourceEval()

  override def destination: Destination =
    this.destination(this.literal(BaseDsl.DEFAULT_DESTINATION_TAG))

  override def destination(source: Source[?]): Destination =
    new DestinationEval(source.asInstanceOf[Eval[Any]])

  override def literal[T](x: T): Source[T] =
    new CalculatedLiteralEval[T](() => x)

  override def literal[T](f: () => T): Source[T] =
    new CalculatedLiteralEval[T](f)

  override def nothing[T]: Source[T] =
    new NothingEval[T]

  override def nil[T]: Source[T] = new CalculatedLiteralEval[T](() =>
    null.asInstanceOf[T])

  override def first[T](source: Source[List[T]]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => 0)

  override def last[T](source: Source[List[T]]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => list.length - 1)

  override def list[T](source: Source[T]): Source[List[T]] =
    new AsListSourceEval[T](source.asInstanceOf[Eval[T]])

  override def by[T](source: Source[PathMap], path: Path): Source[T] =
    new ByEval[T](source.asInstanceOf[Eval[PathMap]], path)

  override def mapElem[T, D](source: Source[List[T]], f: Source[T] => Source[D]): Source[List[D]] =
    new MapElemEval[T, D](source.asInstanceOf[Eval[List[T]]], f)
}

object BaseDsl {
  private val DEFAULT_DESTINATION_TAG: String = "<<default>>"
}