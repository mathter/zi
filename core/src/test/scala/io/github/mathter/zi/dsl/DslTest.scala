package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.Dsl.{*, given}
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.{Eval, Evaluator}
import org.junit.jupiter.api.{Assertions, Test}

class DslTest {
  @Test
  def test(): Unit = {
    val context = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()

    val s = dsl.literal(List(1, 2, 3))
    val s0: Source[String] = "<>"

    val s1 = dsl.destination
      .by[Int]("test")
      .from(s.last)

    val r = s1.asInstanceOf[Eval[?]].eval(context)

    val n = dsl.literal(Option.empty)


    println(r)
  }

  @Test
  def testCustomArgInt(): Unit = {
    implicit val context = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s0 = dsl.literal(1)
    val s1 = s0.customMap(_ + 1)

    Assertions.assertEquals(2, Evaluator.eval(s1))
  }

  @Test
  def testCustomArgNohting(): Unit = {
    implicit val context = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s0: Source[Int] = dsl.nothing
    val s1 = s0.customMap(_ + 1)

    Assertions.assertEquals(Option.empty, Evaluator.eval(s1))
  }

  @Test
  def testCustomWithNothingArgInt(): Unit = {
    implicit val context = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s0 = dsl.literal(1)
    val s1 = s0.customMap(_ + 1)

    Assertions.assertEquals(2, Evaluator.eval(s1))
  }
}
