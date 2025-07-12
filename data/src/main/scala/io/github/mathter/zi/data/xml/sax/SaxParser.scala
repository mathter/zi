package io.github.mathter.zi.data.xml.sax

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.data.xml.XmlParser
import org.xml.sax.InputSource

import javax.xml.parsers.SAXParser

private class SaxParser(private val saxParser: SAXParser) extends XmlParser {
  private val handler = new SaxHandler

  override def parse(is: InputSource): PathMap = {
    this.saxParser.parse(is, this.handler)
    handler.result
  }
}
