
given Monoid[String] with
  extension (x: String) def combine(y: String): String = x ++ y
  def unit = ""
  