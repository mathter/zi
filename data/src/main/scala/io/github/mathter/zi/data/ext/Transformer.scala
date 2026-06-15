package io.github.mathter.zi.data.ext

import javax.xml.transform.Source

trait Transformer {
  def addListener(listener: Listener*): Unit

  def removeListener(listener: Listener): Unit

  def resolve(source: Source): Unit
}
