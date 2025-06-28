package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.*
import io.github.mathter.zi.dsl.base.eval.*
import io.github.mathter.zi.eval.Eval
import io.github.mathter.zi.path.Path

import scala.reflect.ClassTag

class BaseDsl extends Dsl {
  implicit val dsl: Dsl = this

  override def origin: Source[PathMap] =
    new OriginSourceEval()

  override def destination: Destination =
    this.destination(this.literal(BaseDsl.DEFAULT_DESTINATION_TAG))

  override def destination(source: Source[?]): Destination =
    new DestinationEval(source.asInstanceOf[Eval[Any]])

  override def literal[T](x: T)(implicit ctag: ClassTag[T]): Source[T] =
    new CalculatedLiteralEval[T](() => x)

  override def literal[T](f: () => T)(implicit ctag: ClassTag[T]): Source[T] =
    new CalculatedLiteralEval[T](f)

  override def nothing[T](implicit ctag: ClassTag[T]): Source[T] =
    new NothingEval[T]

  override def nil[T](implicit ctag: ClassTag[T]): Source[T] = new CalculatedLiteralEval[T](() =>
    null.asInstanceOf[T])

  override def first[T](source: Source[List[T]])(implicit ctag: ClassTag[T]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => 0)

  override def last[T](source: Source[List[T]])(implicit ctag: ClassTag[T]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], list => list.length - 1)

  override def asList[T](source: Source[T])(implicit classTag: ClassTag[T]): Source[List[T]] =
    new AsListSourceEval[T](source.asInstanceOf[Eval[T]])

  override def by[T](source: Source[PathMap], path: Path)(implicit ctag: ClassTag[T]): Source[T] =
    new ByEval[T](source.asInstanceOf[Eval[PathMap]], path)

  override def mapElem[T, D](source: Source[List[T]], f: Source[T] => Source[D]): Source[List[D]] =
    new MapElemEval[T, D](source.asInstanceOf[Eval[List[T]]], f)
}

object BaseDsl {
  private val DEFAULT_DESTINATION_TAG: String = "<<default>>"
}