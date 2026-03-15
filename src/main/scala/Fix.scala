case class Fix[F[_]](unfix: F[Fix[F]])

object Fix {
  def cata[F[_], A](algebra: F[A] => A)(fix: Fix[F])(using Functor[F]): A =
    algebra(fix.unfix.map(cata(algebra)))
}
