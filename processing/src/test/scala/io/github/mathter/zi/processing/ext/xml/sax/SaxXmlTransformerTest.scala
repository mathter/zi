package io.github.mathter.zi.processing.ext.xml.sax

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.processing.ext.AbstractListener
import io.github.mathter.zi.processing.json.JsonSerializer
import org.junit.jupiter.api.Test

import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.stream.StreamSource
import scala.util.Using

class SaxXmlTransformerTest {
  @Test
  def test(): Unit = {
    val serializer = JsonSerializer()
    val t = new SaxXmlTransformer(SAXParserFactory.newNSInstance())
    val listener = new AbstractListener(
      "book/content/chapter",
      Set("book/authors", "book/isbn")
    ) {
      override def apply(pathMap: PathMap): Unit = {
        println(serializer.serialize(pathMap))
      }
    }

    t.addListener(listener)

    val tr = Using(getClass.getResourceAsStream("/book.xml")) {
      is =>
        val source = StreamSource(is, "book.xml")
        t.resolve(source)
    }
  }
}
