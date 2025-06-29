package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.Evaluator
import org.junit.jupiter.api.{Assertions, Test}

class SourcePathMapTest {
  @Test
  def testBy(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()

    val pm = PathMap.empty
    pm("path0/path1") = "value1"
    pm("path0") = "valu0"

    val s = dsl.literal(pm)

    val result = Evaluator.eval(s)
    Assertions.assertTrue(result.isDefined)
    Assertions.assertEquals("value1", result.get("path0/path1").get)
    Assertions.assertEquals(2, result.flatMap(_[List[Any]]("path0").map(_.length)).get)
  }
}
