package io.github.mathter.zi.dsl

import io.github.mathter.zi.dsl.Source

trait ListSourceOps[E] {
  def first: Source[E]

  def last: Source[E]

  def index(source: Source[Int]): Source[E]

  def mapElem[D](f: Source[E] => Source[D]): Source[List[D]]
}
