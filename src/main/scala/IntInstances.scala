
object Additive {
  given intAdditiveMonoid: Monoid[Int] with
    def unit = 0
    extension (x: Int) def combine(y: Int): Int = x + y
}

object Multiplicative {
  given intMultiplicativeMonoid: Monoid[Int] with
    def unit = 1
    extension (x: Int) def combine(y: Int): Int = x * y
}
