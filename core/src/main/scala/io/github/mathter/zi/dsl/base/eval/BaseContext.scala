package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.eval.Context

import scala.collection.mutable

class BaseContext(val origin: PathMap, val destinations: mutable.Map[Any, PathMap] = mutable.HashMap.empty) extends Context {
  override def destination(tag: Any): PathMap = this.destinations.getOrElseUpdate(tag, PathMap.empty)

  override def destination(tag: Any, pathMap: PathMap): Unit = this.destinations.put(tag, pathMap)
}
