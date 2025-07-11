package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.*
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.{Evaluator, Terminal}
import org.junit.jupiter.api.{Assertions, Test}

class DslTest {
  @Test
  def literalTest(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("Hello")

    Assertions.assertEquals(Opt("Hello"), Evaluator.eval(s))
  }

  @Test
  def testNothing(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.nothing[String]

    Assertions.assertEquals(Opt.empty, Evaluator.eval(s))
  }

  @Test
  def testNil(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.nil[String]

    Assertions.assertEquals(Opt(null), Evaluator.eval(s))
  }

  @Test
  def testCustomOptArgInt(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s0 = dsl.literal(1)
    val s1 = s0.customOpt(option => option.map(_ + 1))

    Assertions.assertEquals(Opt(2), Evaluator.eval(s1))
  }

  @Test
  def testCustomOptArgNohting(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s0: Source[Int] = dsl.nothing
    val s1 = s0.customOpt(option => option.map(_ + 1))

    Assertions.assertEquals(Opt.empty, Evaluator.eval(s1))
  }

  @Test
  def testCustom(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s: Source[String] = dsl.literal("Hello").custom(s => s.toUpperCase)

    Assertions.assertEquals("HELLO", Evaluator.eval(s).get)
  }

  @Test
  def testComposite(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s: Source[String] = dsl.literal("Hello").composite(dsl.literal(" World!")).fun(_ + _)

    Assertions.assertEquals("Hello World!", Evaluator.eval(s).get)
  }

  @Test
  def testDestinationFromPathMap(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val pm = PathMap.empty
    val s = dsl.literal(pm)

    val terminal = dsl.destination.from(s)
    Evaluator.eval(terminal.asInstanceOf[Terminal])

    Assertions.assertEquals(1, context.destinations.size)
    Assertions.assertEquals(pm, context.destinations.values.head)
  }

  @Test
  def testList(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(10).list
    val result = Evaluator.eval(s)

    Assertions.assertEquals(1, result.get.size)
    Assertions.assertEquals(10, result.get.head)
  }

  @Test
  def testObj0(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.obj

    Assertions.assertNotNull(Evaluator.eval(s))
  }

  @Test
  def testObj1(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.obj
    val s0 = s.by[String]("level0/level1").from(dsl.literal("Hello World!"))

    Evaluator.eval(s0.asInstanceOf[Source[String]])
    val result = Evaluator.eval(s).get
    Assertions.assertNotNull(result)
    Assertions.assertEquals("Hello World!", result("level0/level1").get)
  }

  @Test
  def testFalse(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.fls
    val v = Evaluator.eval(s)
    Assertions.assertFalse(v.get)
  }

  @Test
  def testTrue(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.tr
    val v = Evaluator.eval(s)
    Assertions.assertTrue(v.get)
  }

  @Test
  def testAs(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal(10).as[AnyVal]

    Assertions.assertEquals(10, Evaluator.eval(s).get)
  }

  @Test
  def testIf0(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.If(dsl.tr).Then(dsl.literal("Then"))

    Assertions.assertEquals("Then", Evaluator.eval(s).get)
  }

  @Test
  def testIf1(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.If(dsl.tr).Then(dsl.literal("Then")).Else(dsl.literal("Else"))

    Assertions.assertEquals("Then", Evaluator.eval(s).get)
  }

  @Test
  def testIf2(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.If(dsl.fls).Then(dsl.literal("Then")).Else(dsl.literal("Else"))

    Assertions.assertEquals("Else", Evaluator.eval(s).get)
  }

  @Test
  def testIf3(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.If(dsl.fls).Then(dsl.literal("Then")).Else(dsl.literal("Else"))
      .If(dsl.tr).Then(dsl.literal("Then2"))

    Assertions.assertEquals("Then2", Evaluator.eval(s).get)
  }

  @Test
  def testIf4(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.If(dsl.fls).Then(dsl.literal("Then")).Else(dsl.literal("Else"))
      .If(dsl.fls).Then(dsl.literal("Then2")).Else(dsl.literal("Else2"))

    Assertions.assertEquals("Else2", Evaluator.eval(s).get)
  }
}
