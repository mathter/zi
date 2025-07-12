package io.github.mathter.zi.data.xml

import io.github.mathter.zi.data.PathMap
import org.xml.sax.InputSource

trait XmlParser {
  def parse(is: InputSource): PathMap
}
