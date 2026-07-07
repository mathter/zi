package io.github.mathter.zi.processing.ext

import io.github.mathter.zi.path.Path

abstract class AbstractListener(val root: Path, val related: Set[Path]) extends Listener {

}
