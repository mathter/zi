package io.github.mathter.zi.path

private case class EPath(val segment: String, val segmentQ: String, val parent: Path, val hc: Int) extends Path {
  private var expandedCache: List[Path] = null;

  private var localCache: Path = null

  override def path(seqment: String): Path = EPathFactory.of(seqment, null, this)

  override def path(seqment: String, seqmentQ: String): Path = EPathFactory.of(seqment, seqmentQ, this)

  override def local: Path = {
    if (this.localCache == null) {
      this.localCache = EPathFactory.of(this.segment, this.segmentQ, null)
    }

    this.localCache
  }

  override def expand: List[Path] = {
    if (this.expandedCache == null) {
      this.expandedCache = if (this.parent != null) this.parent.expand :+ this else List(this)
    }

    this.expandedCache
  }

  override def hashCode(): Int = this.hc

  override def equals(obj: Any): Boolean = this.asInstanceOf[AnyRef] eq obj.asInstanceOf[AnyRef]

  override def canEqual(that: Any): Boolean = that.isInstanceOf[EPath]

  override def toString: String = {
    if (parent != null) {
      this.parent.toString + Path.DELIMITER + this.localName
    } else {
      this.localName
    }
  }
}