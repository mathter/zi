package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.Evaluator
import org.junit.jupiter.api.{Assertions, Test}

class SourceTest {
  @Test
  def testMapType(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("10").map[Int]

    Assertions.assertEquals(10, Evaluator.eval(s).get)
  }
}
