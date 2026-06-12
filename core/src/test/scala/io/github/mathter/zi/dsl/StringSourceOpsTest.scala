package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.*
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.{BaseContext, Evaluator}
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.{Assertions, Test}

class StringSourceOpsTest {
  @Test
  def testUpperCase(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("Hello").toUpperCase

    Assertions.assertEquals("HELLO", Evaluator.evalSource(s).get)
  }

  @Test
  def testLowerCase(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("Hello").toLowerCase

    Assertions.assertEquals("hello", Evaluator.evalSource(s).get)
  }

  @Test
  def testReplace(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("Hello").replace("^.*(ll).*$", "$1")

    Assertions.assertEquals("ll", Evaluator.evalSource(s).get)

    val a: String => String = s => s.replaceAll("^.*(ll).*$", "$1")
    val s0 = dsl.literal("Hello").custom(a)

    Assertions.assertEquals("ll", Evaluator.evalSource(s0).get)
  }

  @Test
  def testLength(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val origin = RandomStringUtils.insecure().nextAlphabetic(10)
    val s = dsl.literal(origin).length

    Assertions.assertNotNull(s)
    Assertions.assertEquals(origin.length, Evaluator.evalSource(s).get)
  }

  @Test
  def testIsEmpty(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val origin = RandomStringUtils.insecure().nextAlphabetic(10)
    val s = dsl.literal(origin).isEmpty

    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testIsEmpty1(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val s = dsl.nothing[String].isEmpty

    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).isEmpty)
  }

  @Test
  def testMatches(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val origin = "This is a simple string"
    val s = dsl.literal(origin).matches("^This is.*$")

    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)
  }

  @Test
  def testMatches1(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val s = dsl.nothing[String].matches("^This is.*$")

    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).isEmpty)
  }
}
