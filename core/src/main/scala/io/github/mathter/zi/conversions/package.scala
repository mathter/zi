package io.github.mathter.zi

import java.lang
import java.time.{LocalDate, LocalDateTime, LocalTime, OffsetDateTime, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.util.UUID

package object conversions {
  given Conversion[String, Byte] with {
    override def apply(x: String): Byte = if (x != null) lang.Byte.parseByte(x) else null.asInstanceOf[Byte]
  }

  given Conversion[String, Short] with {
    override def apply(x: String): Short = if (x != null) lang.Short.parseShort(x) else null.asInstanceOf[Short]
  }

  given Conversion[String, Int] with {
    override def apply(x: String): Int = if (x != null) Integer.parseInt(x) else null.asInstanceOf[Int]
  }

  given Conversion[String, Long] with {
    override def apply(x: String): Long = if (x != null) lang.Long.parseLong(x) else null.asInstanceOf[Long]
  }

  given Conversion[String, Float] with {
    override def apply(x: String): Float = if (x != null) lang.Float.parseFloat(x) else null.asInstanceOf[Float]
  }

  given Conversion[String, Double] with {
    override def apply(x: String): Double = if (x != null) lang.Double.parseDouble(x) else null.asInstanceOf[Double]
  }

  given Conversion[String, BigInt] with {
    override def apply(x: String): BigInt = if (x != null) BigInt(x) else null.asInstanceOf[BigInt]
  }

  given Conversion[String, BigDecimal] with {
    override def apply(x: String): BigDecimal = if (x != null) BigDecimal(x) else null.asInstanceOf[BigDecimal]
  }

  given Conversion[String, UUID] with {
    override def apply(x: String): UUID = if (x != null) UUID.fromString(x) else null.asInstanceOf[UUID]
  }

  given Conversion[LocalDateTime, LocalTime] with {
    override def apply(x: LocalDateTime): LocalTime = x.toLocalTime
  }

  given Conversion[LocalDateTime, LocalDate] with {
    override def apply(x: LocalDateTime): LocalDate = x.toLocalDate
  }

  given Conversion[LocalDate, LocalTime] with {
    override def apply(x: LocalDate): LocalTime = LocalTime.of(0, 0)
  }

  implicit class String2LocalDate(implicit val formatter: DateTimeFormatter) extends Conversion[String, LocalDate] {
    override def apply(x: String): LocalDate =
      if (x != null) LocalDate.from(this.formatter.parse(x)) else null.asInstanceOf[LocalDate]
  }

  implicit class String2LocalDateTime(implicit val formatter: DateTimeFormatter) extends Conversion[String, LocalDateTime] {
    override def apply(x: String): LocalDateTime =
      if (x != null) LocalDateTime.from(this.formatter.parse(x)) else null.asInstanceOf[LocalDateTime]
  }

  implicit class String2LocalTime(implicit val formatter: DateTimeFormatter) extends Conversion[String, LocalTime] {
    override def apply(x: String): LocalTime =
      if (x != null) LocalTime.from(this.formatter.parse(x)) else null.asInstanceOf[LocalTime]
  }

  implicit class String2OffsetDateTime(implicit val formatter: DateTimeFormatter) extends Conversion[String, OffsetDateTime] {
    override def apply(x: String): OffsetDateTime =
      if (x != null) OffsetDateTime.from(this.formatter.parse(x)) else null.asInstanceOf[OffsetDateTime]
  }

  implicit class String2ZonedDateTime(implicit val formatter: DateTimeFormatter) extends Conversion[String, ZonedDateTime] {
    override def apply(x: String): ZonedDateTime =
      if (x != null) ZonedDateTime.from(this.formatter.parse(x)) else null.asInstanceOf[ZonedDateTime]
  }
}
