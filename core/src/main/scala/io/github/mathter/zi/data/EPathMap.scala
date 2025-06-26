package io.github.mathter.zi.mem

import io.github.mathter.zi.path.Path

import scala.collection.mutable

private class EPathMap(private val map: InnerMap = new InnerMap) extends PathMap {
  override def apply[T](path: Path): T = {
    val paths = path.expand.map(_.local)
    val valuesMapList: List[InnerMap] = if (paths.length > 1) {
      paths
        .take(if (paths.length > 1) paths.length - 1 else 1)
        .foldLeft(List(this.map))((l: List[InnerMap], r) => getSubMaps(l, r))
    } else {
      List(this.map)
    }
    val values = valuesMapList
      .flatMap(innerMap => innerMap.getOrElse(paths.last, null))
      .map(reverseTranslate)

    values.length match {
      case 0 => Option.empty.asInstanceOf[T]
      case 1 => values.head.asInstanceOf[T]
      case _ => values.asInstanceOf[T]
    }
  }

  def getSubMaps(list: List[InnerMap], path: Path): List[InnerMap] = {
    list.flatMap(innerMap => innerMap.get(path)
      .map(e => e.filter(e => e != null && e.isInstanceOf[InnerMap])
        .map(_.asInstanceOf[InnerMap])).getOrElse(List()))
  }

  override def update[T](path: Path, value: T): Unit = {
    var tmp = this.map
    val paths = path.expand.map(_.local)

    for (i <- 0 until (paths.length - 1)) {
      val element = tmp.getOrElse(paths(i), null)

      if (element == null) {
        val newMap = new InnerMap
        val newQueue = EPathMap.newQueue :+ newMap
        tmp.put(paths(i), newQueue)
        tmp = newMap
      } else {
        tmp = element
          .filter(_.isInstanceOf[InnerMap])
          .map(_.asInstanceOf[InnerMap])
          .head
      }
    }

    tmp.getOrElseUpdate(paths.last, EPathMap.newQueue).addOne(this.translate(value))
  }

  private def reverseTranslate(value: Any): Any = {
    value match {
      case map: InnerMap => new EPathMap(map)
      case _ => value
    }
  }

  private def translate(value: Any): Any = {
    value match {
      case map: EPathMap => map.map
      case _ => value
    }
  }
}

private object EPathMap {
  private def newQueue: mutable.Queue[Any] = mutable.Queue.empty
}

private class InnerMap extends mutable.HashMap[Path, mutable.Queue[Any]] {
}