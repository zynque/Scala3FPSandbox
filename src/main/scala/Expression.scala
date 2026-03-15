 import Expression.*
 import FixedExpressions.*

enum Expression[A]:
  case Lit(value: Int)
  case Add(left: A, right: A)
  case Mul(left: A, right: A)

object Expression:
  given Functor[Expression] with
    def fmap[A, B](fa: Expression[A])(f: A => B): Expression[B] = fa match
      case Lit(value) => Lit(value)
      case Add(left, right) => Add(f(left), f(right))
      case Mul(left, right) => Mul(f(left), f(right))

object FixedExpressions:
  type FixedExpression = Fix[Expression]
  def lit(value: Int): FixedExpression = Fix(Lit(value))
  def add(left: FixedExpression, right: FixedExpression): FixedExpression = Fix(Add(left, right))
  def mul(left: FixedExpression, right: FixedExpression): FixedExpression = Fix(Mul(left, right))

object ExpressionAlgebras:
  val evalAlgebra: Expression[Int] => Int = {
    case Lit(value) => value
    case Add(left, right) => left + right
    case Mul(left, right) => left * right
  }
  val printAlgebra: Expression[String] => String = {
    case Lit(value) => value.toString
    case Add(left, right) => s"($left + $right)"
    case Mul(left, right) => s"($left * $right)"
  }

  def eval(expr: FixedExpression): Int =
    Fix.cata(evalAlgebra)(expr)
  
  def print(expr: FixedExpression): String =
    Fix.cata(printAlgebra)(expr)
