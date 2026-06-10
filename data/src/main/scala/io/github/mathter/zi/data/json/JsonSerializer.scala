package io.github.mathter.zi.data.json

import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.json.JsonMapper
import io.github.mathter.zi.data.{PathMap, Serializer}

class JsonSerializer(val objectMapper: ObjectMapper = JsonMapper.builder().build()) extends Serializer[String] {
  override def serialize(pathMap: PathMap): String = {

    val v = pathMap.toJavaMap(p => p.segment)
    this.objectMapper.writeValueAsString(pathMap.toJavaMap(p => p.segment))
  }
}