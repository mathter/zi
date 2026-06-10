package io.github.mathter.zi.dsl.base

import io.github.mathter.zi.conversions.{Conversions, DefaultConversions}
import io.github.mathter.zi.data.{Opt, PathMap}
import io.github.mathter.zi.dsl.base.BaseDsl.DEFAULT_DESTINATION_TAG
import io.github.mathter.zi.dsl.base.eval.*
import io.github.mathter.zi.dsl.{Acceptor, Dsl, Group, If, NumericSourceOps, Source}
import io.github.mathter.zi.eval.{Context, Eval}
import io.github.mathter.zi.path.Path

class BaseDsl(val conversions: Conversions = DefaultConversions.default) extends Dsl {
  implicit private val dsl: Dsl = this

  override def origin: Source[PathMap] = new OriginSourceEval()

  override def result[T]: Acceptor[T] = this.result(this.literal(DEFAULT_DESTINATION_TAG))

  override def result[T](tag: Source[Any]): Acceptor[T] = new ResultEval[T](tag.asInstanceOf[Eval[Any]])

  override def obj: Acceptor[PathMap] = new PathMapAcceptor

  override def literal[T](x: T): Source[T] =
    new CalculatedLiteralEval[T](() => x)

  override def literal[T](f: () => T): Source[T] =
    new CalculatedLiteralEval[T](f)

  override def nothing[T]: Source[T] =
    new NothingEval[T]

  override def nil[T]: Source[T] = new CalculatedLiteralEval[T](() =>
    null.asInstanceOf[T])

  override def fls: Source[Boolean] = this.literal(false)

  override def tr: Source[Boolean] = this.literal(true)

  def first[T](source: Source[List[T]]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], new AbstractEval[Int]() {
      override def evalI(context: Context): Opt[Int] = Opt(0)
    })

  def last[T](source: Source[List[T]]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], new AbstractEval[Int]() {
      override def evalI(implicit context: Context): Opt[Int] = source.asInstanceOf[Eval[List[T]]].eval.map(_.length - 1)
    })

  def index[T](source: Source[List[T]], index: Source[Int]): Source[T] =
    new ListElementByIndexEval[T](source.asInstanceOf[Eval[List[T]]], index.asInstanceOf[Eval[Int]])

  override def list[T](source: Source[T]): Source[List[T]] =
    new AsListSourceEval[T](source.asInstanceOf[Eval[T]])

  override def by[T](source: Source[PathMap], path: Path): Source[T] =
    new ByEval[T](source.asInstanceOf[Eval[PathMap]], path)

  override def by[T](source: Acceptor[PathMap], path: Path): Acceptor[T] =
    new PathMapByPathAcceptor[T](source.asInstanceOf[Eval[PathMap]], path)


  override def mapElem[T, D](source: Source[List[T]], f: Source[T] => Source[D]): Source[List[D]] = {
    new MapElemEval[T, D](source.asInstanceOf[Eval[List[T]]], f)
  }

  override def If[T](condition: Source[Boolean]): If[T] =
    new IfEval[T](condition.asInstanceOf[Eval[Boolean]])

  override def group[K, E](source: Source[List[E]], key: Source[E] => Source[K]): Group[K, E] =
    new GroupEval[K, E](source.asInstanceOf[Eval[List[E]]], key)

  override def filter[T](source: Source[List[T]], p: Source[T] => Source[Boolean]): Source[List[T]] =
    new FilterEval[T](source.asInstanceOf[Eval[List[T]]], p)

  override def distinctBy[K, T](source: Source[List[T]], key: Source[T] => Source[K]): Source[List[T]] =
    new DistinctEval[K, T](source.asInstanceOf[Eval[List[T]]], key)

  override def shortSourceOps(x: Source[Short]): NumericSourceOps[Short] =
    new ShortSourceOps(x)

  override def intSourceOps(x: Source[Int]): NumericSourceOps[Int] =
    new IntSourceOps(x)

  override def longSourceOps(x: Source[Long]): NumericSourceOps[Long] =
    new LongSourceOps(x)

  def floatSourceOps(x: Source[Float]): NumericSourceOps[Float] =
    new FloatSourceOps(x)

  override def doubleSourceOps(x: Source[Double]): NumericSourceOps[Double] =
    new DoubleSourceOps(x)

  override def bigIntSourceOps(x: Source[BigInt]): NumericSourceOps[BigInt] =
    new BigIntSourceOps(x)

  override def bigDecimalSourceOps(x: Source[BigDecimal]): NumericSourceOps[BigDecimal] =
    new BigDecimalSourceOps(x)
}

object BaseDsl {
  private val DEFAULT_DESTINATION_TAG: String = "<<default>>"

  implicit def baseDsl: BaseDsl = new BaseDsl
}