package io.github.mathter.zi.data.ext.xml.sax

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.data.ext.AbstractListener
import io.github.mathter.zi.data.json.JsonSerializer

import java.io.InputStream
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.stream.StreamSource
import scala.util.Using

object LoadTest {
  val startBook =
    """
      |<book>
      | <title>Clean Code: A Handbook of Agile Software Craftsmanship</title>
      | <authors>
      |   <author>Robert C. Martin</author>
      | </authors>
      | <isbn>978-0-13-235088-4</isbn>
      | <content>""".stripMargin

  val endBook =
    """
      | </content>
      |</book>
      |""".stripMargin

  val chapter =
    """
      |   <chapter>
      |     <title>Chapter 1: Clean Code</title>
      |     <date>2026-06-11</date>
      |     <page>
      |       <number>1</number>
      |       <count>50</count>
      |     </page>
      |     <description>This chapter devoited to clean code bla bla bla</description>
      |     <description1>This chapter devoited to clean code bla bla bla</description1>
      |     <description2>This chapter devoited to clean code bla bla bla</description2>
      |     <vol>1</vol>
      |     <content>
      |      <item>
      |       <title>Item 1</title>
      |       <page>
      |         <number>1</number>
      |       </page>
      |      </item>
      |     </content>
      |   </chapter>
      |""".stripMargin

  def main(args: Array[String]): Unit = {
    val serializer = JsonSerializer()
    val t = new SaxXmlTransformer(SAXParserFactory.newNSInstance())
    val listener = new AbstractListener(
      "book/content/chapter",
      Set("book/authors", "book/isbn")
    ) {
      override def apply(pathMap: PathMap): Unit = {
        // Do nothing
      }
    }
    t.addListener(listener)

    val n = 1_000_000
    Using(gen(n)) {
      is =>
        val source = StreamSource(is, "book.xml")

        val start = System.nanoTime()
        t.resolve(source)
        println((System.nanoTime() - start) / 1_000_000_000.0 / n)
    }.recover(e => e.printStackTrace())
  }

  def gen(n: Int): InputStream = {
    new InputStream {
      var i = 0
      var position = 0
      var bytes: Array[Byte] = null

      override def read(): Int = {
        var result: Int | Null = null

        while (result == null) {
          if (bytes != null && position < bytes.length) {
            result = bytes(position)
            position += 1
          } else {
            result = null

            if (i == 0 && position == 0) {
              bytes = startBook.getBytes
            } else if (i < n) {
              bytes = chapter.getBytes
              position = 0
              i += 1
            } else if (i == n) {
              bytes = endBook.getBytes
              position = 0
              i += 1
            } else {
              result = -1
            }
          }
        }

        result.asInstanceOf[Int]
      }
    }
  }
}
