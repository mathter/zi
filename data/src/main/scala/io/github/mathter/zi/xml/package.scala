package io.github.mathter.zi

package object xml {
  extension (x: String) {
    inline def trimToNull: String = {
      if (x != null && "" == x.trim) {
        null
      } else {
        x
      }
    }
  }
}
