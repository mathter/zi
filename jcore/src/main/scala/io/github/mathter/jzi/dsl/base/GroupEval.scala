package io.github.mathter.jzi.dsl.base

import io.github.mathter.jzi.dsl.Group
import io.github.mathter.zi.data.Opt
import io.github.mathter.zi.dsl
import io.github.mathter.zi.dsl.base.eval.AbstractEval
import io.github.mathter.zi.dsl.base.given
import io.github.mathter.zi.dsl.{Dsl, Source, Group as zGroup}
import io.github.mathter.zi.eval.{Context, Tracer}
import org.apache.commons.lang3.tuple.Pair

import java.util

class GroupEval[K, T](group: zGroup[K, T])
                     (implicit dsl: Dsl, tracer: Tracer = Tracer.trace5())
  extends ListSourceEval[Pair[K, util.List[T]]](null) with Group[K, T] {
  override def evalI(using context: Context): Opt[util.List[Pair[K, util.List[T]]]] = {
    import scala.jdk.CollectionConverters.given

    this.group.eval.map(e => e.map((key, list) => Pair.of(key, list.asJava)).asJava)
  }
}