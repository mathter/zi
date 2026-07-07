package io.github.mathter.zi.data

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
