package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

trait OrderedSourceOps[T] {
  def compare(that: Source[T]): Source[Int]

  infix inline def <(that: Source[T]): Source[Boolean] = this.compare(that).custom(e => e < 0)

  infix inline def >(that: Source[T]): Source[Boolean] = this.compare(that).custom(e => e > 0)

  infix inline def <=(that: Source[T]): Source[Boolean] = this.compare(that).custom(e => e <= 0)

  infix inline def >=(that: Source[T]): Source[Boolean] = this.compare(that).custom(e => e >= 0)
}

implicit class OrderedSource1Ops[T <: Ordered[T]](x: Source[T]) extends OrderedSourceOps[T] {
  infix inline override def compare(that: Source[T]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceShortOps(x: Source[Short]) extends OrderedSourceOps[Short] {
  infix inline override def compare(that: Source[Short]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceIntOps(x: Source[Int]) extends OrderedSourceOps[Int] {
  infix inline override def compare(that: Source[Int]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceLongOps(x: Source[Long]) extends OrderedSourceOps[Long] {
  infix inline override def compare(that: Source[Long]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceFloatOps(x: Source[Float]) extends OrderedSourceOps[Float] {
  infix inline override def compare(that: Source[Float]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceDoubleOps(x: Source[Double]) extends OrderedSourceOps[Double] {
  infix inline override def compare(that: Source[Double]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceBigIntOps(x: Source[BigInt]) extends OrderedSourceOps[BigInt] {
  infix inline override def compare(that: Source[BigInt]): Source[Int] = x.composite(that).fun(_ compare _)
}

implicit class OrderedSourceBigDecimalOps(x: Source[BigDecimal]) extends OrderedSourceOps[BigDecimal] {
  infix inline override def compare(that: Source[BigDecimal]): Source[Int] = x.composite(that).fun(_ compare _)
}