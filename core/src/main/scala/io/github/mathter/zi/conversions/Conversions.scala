package io.github.mathter.zi.conversions

import java.time.{LocalDate, LocalDateTime, LocalTime, OffsetDateTime, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.util.UUID
import scala.reflect.ClassTag

trait Conversions {
  def map[T, D](value: T)(implicit classTag: ClassTag[D]): D
}
