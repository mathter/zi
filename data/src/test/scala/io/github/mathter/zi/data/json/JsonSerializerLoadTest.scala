package io.github.mathter.zi.data.json

import io.github.mathter.zi.data.xml.sax.ParserFactory
import org.junit.jupiter.api.Test

import scala.xml.InputSource

class JsonSerializerLoadTest {
  @Test
  def test(): Unit = {
    val is = new InputSource(classOf[JsonSerializerLoadTest].getClassLoader.getResourceAsStream("book.xml"))
    val pm = ParserFactory.newNSInstance().xmlParser.parse(is)
    val serializer = JsonSerializer()

    val start = System.nanoTime()
    for (i <- 0 to 1_000_000) {
      serializer.serialize(pm)
    }
    println((System.nanoTime() - start) / 1_000_000_000.0)
  }
}
