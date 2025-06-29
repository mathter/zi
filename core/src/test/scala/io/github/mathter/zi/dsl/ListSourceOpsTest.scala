package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.*
import io.github.mathter.zi.dsl.base.*
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

  @Test
  def testIndex(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(List(1, 2, 3, 4, 5)).index(dsl.literal(2))

    Assertions.assertEquals(3, Evaluator.eval(s).get)
  }

  @Test
  def testMapElem(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(List(1, 2, 3)).mapElem(e => e + dsl.literal(1))

    Assertions.assertEquals(List(2, 3, 4), Evaluator.eval(s).get)
  }
}
