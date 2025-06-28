package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.Evaluator
import org.junit.jupiter.api.{Assertions, Test}

class ListSourceOpsTest {
  @Test
  def testFirst(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(List(1, 2, 3)).first
    val v = Evaluator.eval(s)

    Assertions.assertEquals(1, Evaluator.eval(s).get)
  }

  @Test
  def testLast(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(List(1, 2, 3)).last
    val v = Evaluator.eval(s)

    Assertions.assertEquals(3, Evaluator.eval(s).get)
  }
}
