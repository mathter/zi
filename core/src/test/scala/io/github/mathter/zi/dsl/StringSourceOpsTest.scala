package io.github.mathter.zi.dsl

import io.github.mathter.zi.data.PathMap
import io.github.mathter.zi.dsl.StringSourceOps.*
import io.github.mathter.zi.dsl.base.BaseDsl
import io.github.mathter.zi.dsl.base.eval.BaseContext
import io.github.mathter.zi.eval.Evaluator
import org.junit.jupiter.api.{Assertions, Test}

class StringSourceOpsTest {
  @Test
  def testReplace(): Unit = {
    implicit val context: BaseContext = new BaseContext(PathMap.empty)
    val dsl: Dsl = BaseDsl()
    val s = dsl.literal("Hello").replace("^.*(ll).*$", "$1")

    Assertions.assertEquals("ll", Evaluator.eval(s).get)

    val a: String => String = s => s.replaceAll("^.*(ll).*$", "$1")
    val s0 = dsl.literal("Hello").custom(a)

    Assertions.assertEquals("ll", Evaluator.eval(s0).get)
  }
}
