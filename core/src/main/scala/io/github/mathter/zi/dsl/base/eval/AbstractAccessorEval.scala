package io.github.mathter.zi.dsl.base.eval

import io.github.mathter.zi.dsl.Accessor
import io.github.mathter.zi.path.Path

abstract class AbstractAccessorEval[T](private val path: Path) extends Accessor[T] {
}
