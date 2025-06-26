package io.github.mathter.zi.data

import java.lang.Comparable

case class Range[T <: Comparable[T]](val left: T, val right: T) {

}
