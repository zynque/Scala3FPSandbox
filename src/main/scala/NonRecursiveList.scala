import NonRecursiveList.*
import FixedNonRecursiveLists.*

enum NonRecursiveList[+T, +A]:
  case Cons(head: T, tail: A)
  case Nil

object NonRecursiveList:
  given [T]: Functor[[A] =>> NonRecursiveList[T, A]] with
    def fmap[A, B](fa: NonRecursiveList[T, A])(f: A => B): NonRecursiveList[T, B] = fa match
      case Cons(head, tail) => Cons(head, f(tail))
      case Nil => Nil

object FixedNonRecursiveLists:
  type FixedNonRecursiveList[T] = Fix[[A] =>> NonRecursiveList[T, A]]
  def nil[T]: FixedNonRecursiveList[T] = Fix(Nil)
  def pure[T](x: T): FixedNonRecursiveList[T] = Fix(Cons(x, nil))
  def makeList[T](xs: T*): FixedNonRecursiveList[T] =
    if xs.isEmpty then nil
    else Fix(Cons(xs.head, makeList(xs.tail: _*)))

object NonRecursiveListAlgebras:
  val lengthAlgebra: NonRecursiveList[Any, Int] => Int = {
    case Cons(_, tail) => 1 + tail
    case Nil => 0
  }
  def toListAlgebra[T]: NonRecursiveList[T, List[T]] => List[T] = {
    case Cons(head, tail) => head :: tail
    case Nil => scala.Nil
  }
  val sumAlgebra: NonRecursiveList[Int, Int] => Int = {
    case Cons(head, tail) => head + tail
    case Nil => 0
  }

  def getLength[T](list: FixedNonRecursiveList[T]): Int =
    Fix.cata(lengthAlgebra)(list)
  
  def toList[T](list: FixedNonRecursiveList[T]): List[T] =
    Fix.cata(toListAlgebra)(list)

  def sum(list: FixedNonRecursiveList[Int]): Int =
    Fix.cata(sumAlgebra)(list)
  