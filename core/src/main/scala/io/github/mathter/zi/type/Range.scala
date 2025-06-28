package io.github.mathter.zi.`type`

import java.lang.Comparable

case class Range[T <: Comparable[T]](val left: T, val right: T) {

}
