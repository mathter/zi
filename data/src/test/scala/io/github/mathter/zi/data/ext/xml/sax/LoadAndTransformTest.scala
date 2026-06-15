package io.github.mathter.zi.data.ext.xml.sax

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.data.ext.AbstractListener
import io.github.mathter.zi.data.json.JsonSerializer
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.{BaseContext, Evaluator}
import io.github.mathter.zi.eval.Terminal

import java.io.InputStream
import java.time.LocalDate
import java.util
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.stream.StreamSource
import scala.util.Using

object LoadAndTransformTest {
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
      |      <item>
      |       <title>Item 2</title>
      |       <page>
      |         <number>2</number>
      |       </page>
      |      </item>
      |     </content>
      |   </chapter>
      |""".stripMargin

  def main(args: Array[String]): Unit = {
    val serializer = JsonSerializer()
    val t = new SaxXmlTransformer(SAXParserFactory.newNSInstance())
    val terms = terminals()
    val listener = new AbstractListener(
      "book/content/chapter",
      Set("book/authors", "book/isbn")
    ) {
      override def apply(pathMap: PathMap): Unit = {
        implicit val context = new BaseContext(pathMap)
        terms.foreach(e => Evaluator.eval(e))
        terms
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

  def terminals(): Set[Terminal] = {
    import io.github.mathter.zi.dsl.given
    implicit val dsl = BaseDsl()

    val result = dsl.obj

    Set(
      result.by("chapter/title").from(dsl.origin.by("chapter/title")),
      result.by("chapter/date").from(dsl.origin.by("chapter/date").map[LocalDate]),
      result.by("chapter/pageInfo/pageNumber").from(dsl.origin.by("chapter/page/number")),
      result.by("chapter/pageInfo/pageCount").from(dsl.origin.by("chapter/page/count")),
      result.by("chapter/val").from(dsl.origin.by("chapter/vol")),
      result.by("chapter/content/items").from(
        dsl.origin
          .by("chapter/content/item")
      ),
      dsl.result.from(result)
    )
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
