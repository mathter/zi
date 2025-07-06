package io.github.mathter.zi.conversions

import org.junit.jupiter.api.{Assertions, Test}

import java.time.{LocalDate, LocalDateTime, LocalTime, OffsetDateTime, ZonedDateTime}
import java.util.UUID

class ConversionTest {
  private val conversions = DefaultConversions.default

  @Test
  def testString2Byte(): Unit = {
    Assertions.assertNull(conversions.map[String, Byte](null))
    Assertions.assertEquals("10", conversions.map[String, Byte]("10").toString)
  }

  @Test
  def testByte2String(): Unit = {
    Assertions.assertEquals("10", conversions.map[Byte, String](10))
  }

  @Test
  def testString2Short(): Unit = {
    Assertions.assertNull(conversions.map[String, Short](null))
    Assertions.assertEquals("10", conversions.map[String, Short]("10").toString)
  }

  @Test
  def testString2Int(): Unit = {
    Assertions.assertNull(conversions.map[String, Int](null))
    Assertions.assertEquals("10", conversions.map[String, Int]("10").toString)
  }

  @Test
  def testString2Long(): Unit = {
    Assertions.assertNull(conversions.map[String, Long](null))
    Assertions.assertEquals("10", conversions.map[String, Long]("10").toString)
  }

  @Test
  def testString2BigInt(): Unit = {
    Assertions.assertNull(conversions.map[String, BigInt](null))
    Assertions.assertEquals("10", conversions.map[String, BigInt]("10").toString)
  }

  @Test
  def testString2BigDecimal(): Unit = {
    Assertions.assertNull(conversions.map[String, BigDecimal](null))
    Assertions.assertEquals("10.01", conversions.map[String, BigDecimal]("10.01").toString)
  }

  @Test
  def testString2UUID(): Unit = {
    val value = UUID.randomUUID()
    Assertions.assertNull(conversions.map[String, UUID](null))
    Assertions.assertEquals(value, conversions.map[String, UUID](value.toString))
  }

  @Test
  def testUUID2String(): Unit = {
    val value = UUID.randomUUID()
    Assertions.assertNull(conversions.map[UUID, String](null))
    Assertions.assertEquals(value.toString, conversions.map[UUID, String](value))
  }

  @Test
  def testString2LocaDate(): Unit = {
    val value = LocalDate.now()
    Assertions.assertNull(conversions.map[String, LocalDate](null))
    Assertions.assertEquals(value, conversions.map[String, LocalDate](value.toString))
  }

  @Test
  def testString2LocaDateTime(): Unit = {
    val value = LocalDateTime.now()
    Assertions.assertNull(conversions.map[String, LocalDateTime](null))
    Assertions.assertEquals(value, conversions.map[String, LocalDateTime](value.toString))
  }

  @Test
  def testString2OffsetDateTime(): Unit = {
    val value = OffsetDateTime.now()
    Assertions.assertNull(conversions.map[String, OffsetDateTime](null))
    Assertions.assertEquals(value, conversions.map[String, OffsetDateTime](value.toString))
  }

  @Test
  def testString2ZonedDateTime(): Unit = {
    val value = ZonedDateTime.now()
    Assertions.assertNull(conversions.map[String, ZonedDateTime](null))
    Assertions.assertEquals(value, conversions.map[String, ZonedDateTime](value.toString))
  }

  @Test
  def testLocaDateTime2LocalTime(): Unit = {
    val value = LocalDateTime.now()
    Assertions.assertNull(conversions.map[LocalDateTime, LocalTime](null))
    Assertions.assertEquals(value.toLocalTime, conversions.map[LocalDateTime, LocalTime](value))
  }

  @Test
  def testLocaDateTime2LocalDate(): Unit = {
    val value = LocalDateTime.now()
    Assertions.assertNull(conversions.map[LocalDateTime, LocalDate](null))
    Assertions.assertEquals(value.toLocalDate, conversions.map[LocalDateTime, LocalDate](value))
  }

  @Test
  def testLocaDate2LocalTime(): Unit = {
    val value = LocalDate.now()
    Assertions.assertNull(conversions.map[LocalDate, LocalTime](null))
    Assertions.assertEquals(LocalTime.of(0, 0), conversions.map[LocalDate, LocalTime](value))
  }
}
