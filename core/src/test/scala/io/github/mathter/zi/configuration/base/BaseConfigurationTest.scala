package io.github.mathter.zi.configuration.base

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.Evaluator
import org.junit.jupiter.api.Test

class BaseConfigurationTest {
  @Test
  def test(): Unit = {
    implicit val context: BaseContext = BaseContext(PathMap.empty)
    val terminals = BaseConfiguration() {
      dsl =>
        dsl.destination {
          dest =>
            dest("level0/level1/number") = dsl.literal(10)

            dest.by[List[Int]]("level0/numbers")
              .from(dsl.literal(List(1, 2, 3)))
        }
    }

    terminals.foreach(terminal => Evaluator.eval(terminal))

    context.destinations.head
  }
}
