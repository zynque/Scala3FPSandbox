
import Additive.intAdditiveMonoid

@main def hello: Unit =
  println("Hello, this is a sandbox!")
  println(34 combine 45)
  println("hello" combine " " combine "world")
  println("hello" combine summon[Monoid[String]].unit)
  println(summon[Pure[List]].pure("a").map( x => "*" + x).flatmap(item => List(item, item)))

val v = "a value"
