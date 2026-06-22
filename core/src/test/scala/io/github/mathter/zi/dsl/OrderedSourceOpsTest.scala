package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.{BaseContext, Evaluator}
import org.junit.jupiter.api.{Assertions, Test}
import org.junit.jupiter.params.provider.ArgumentsProvider

class OrderedSourceOpsTest extends ArgumentsProvider {
  @Test
  def testShortOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(10.asInstanceOf[Short])
    val rightSource = dsl.literal(20.asInstanceOf[Short])

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testIntOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(10)
    val rightSource = dsl.literal(20)

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testLongOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(10L)
    val rightSource = dsl.literal(20L)

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testFloatOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(10f)
    val rightSource = dsl.literal(20f)

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testBigIntOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(BigInt(10))
    val rightSource = dsl.literal(BigInt(20))

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }

  @Test
  def testBigDecimalOrdered(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    implicit val dsl: Dsl = BaseDsl()
    val leftSource = dsl.literal(BigDecimal(10))
    val rightSource = dsl.literal(BigDecimal(20))

    var s = leftSource < rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource <= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertTrue(Evaluator.evalSource(s).get)

    s = leftSource > rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)

    s = leftSource >= rightSource
    Assertions.assertNotNull(s)
    Assertions.assertFalse(Evaluator.evalSource(s).get)
  }
}
