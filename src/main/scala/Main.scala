
import Additive.intAdditiveMonoid
import FixedExpressions.*
import FixedNonRecursiveLists.*
import NonRecursiveListAlgebras.*
import ExpressionAlgebras.*

@main def hello: Unit =
  println("Scala 3 FP Sandbox!")
  fAlgebraExamples

def algebraExamples: Unit =
  println("Algebra!")
  println(34 combine 45)
  println("hello" combine " " combine "world")
  println("hello" combine summon[Monoid[String]].unit)
  println(summon[Pure[List]].pure("a").map( x => "*" + x).flatmap(item => List(item, item)))

def fAlgebraExamples: Unit =
  println("F-Algebras!")
  println("EXPRESSIONS: ")
  val expr: FixedExpression = add(lit(3), mul(lit(4), lit(5)))
  println(expr)
  println(print(expr))
  println(eval(expr))

  println()
  println("LISTS: ")
  val list: FixedNonRecursiveList[Int] = makeList(1, 2, 3)
  println(list)
  println(getLength(list))
  println(toList(list))
  println(sum(list))
