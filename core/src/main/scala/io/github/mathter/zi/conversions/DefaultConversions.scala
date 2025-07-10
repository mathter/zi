package io.github.mathter.zi.conversions

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.UUID
import scala.reflect.ClassTag

class DefaultConversions(private val conversions: Map[(Class[?], Class[?]), Conversion[?, ?]]) extends Conversions {
  private val toStringConversion: Option[Conversion[Any, String]] = Option((x: Any) => if (x != null) x.toString else null)

  override def map[T, D](value: T)(implicit classTag: ClassTag[D]): D = {
    if (value != null) {
      val conversion: Option[Conversion[T, D]] =
        if (classOf[String] == classTag.runtimeClass) {
          this.toStringConversion.asInstanceOf[Option[Conversion[T, D]]]
        } else {
          this.conversions.get((value.getClass, classTag.runtimeClass)).asInstanceOf[Option[Conversion[T, D]]]
        }

      conversion.map(_.asInstanceOf[Conversion[T, D]](value)).get
    } else {
      null.asInstanceOf[D]
    }
  }


  given Conversion[Any, String] with {
    override def apply(x: Any): String = if (x == null) null else x.toString
  }
}

object DefaultConversions {
  val localDateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

  val localDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

  val localTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

  val offsetDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

  val zonedDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

  val defaultConversionMap: Map[(Class[?], Class[?]), Conversion[?, ?]] = Map(
    (classOf[String], classOf[Byte]) -> given_Conversion_String_Byte,
    (classOf[String], classOf[Short]) -> given_Conversion_String_Short,
    (classOf[String], classOf[Int]) -> given_Conversion_String_Int,
    (classOf[String], classOf[Long]) -> given_Conversion_String_Long,
    (classOf[String], classOf[Float]) -> given_Conversion_String_Float,
    (classOf[String], classOf[Double]) -> given_Conversion_String_Double,
    (classOf[String], classOf[BigInt]) -> given_Conversion_String_BigInt,
    (classOf[String], classOf[BigDecimal]) -> given_Conversion_String_BigDecimal,
    (classOf[String], classOf[UUID]) -> given_Conversion_String_UUID,
    (classOf[LocalDateTime], classOf[LocalTime]) -> given_Conversion_LocalDateTime_LocalTime,
    (classOf[LocalDateTime], classOf[LocalDate]) -> given_Conversion_LocalDateTime_LocalDate,
    (classOf[LocalDate], classOf[LocalTime]) -> given_Conversion_LocalDate_LocalTime,
    (classOf[String], classOf[LocalDate]) -> new String2LocalDate(using this.localDateFormatter),
    (classOf[String], classOf[LocalDateTime]) -> new String2LocalDateTime(using this.localDateTimeFormatter),
    (classOf[String], classOf[LocalTime]) -> new String2LocalTime()(using this.localTimeFormatter),
    (classOf[String], classOf[OffsetDateTime]) -> new String2OffsetDateTime(using this.offsetDateTimeFormatter),
    (classOf[String], classOf[ZonedDateTime]) -> new String2ZonedDateTime()(using this.zonedDateTimeFormatter)
  )

  private val DEFAULT = new DefaultConversions(this.defaultConversionMap)

  def default: Conversions = DEFAULT
}