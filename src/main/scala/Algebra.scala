trait Semigroup[A]:
  extension (x: A) def combine(y: A): A

trait Monoid[A] extends Semigroup[A]:
  def unit: A

trait Pure[F[_]]:
  def pure[A](x: A): F[A]
  
trait Functor[F[_]]:
  def fmap[A, B](fa: F[A])(f: A => B): F[B]
  extension [A] (fa: F[A]) def map[B](f: A => B): F[B] = fmap(fa)(f)

trait Monad[F[_]] extends Functor[F] with Pure[F]:
  def flatmap2[A, B](fa: F[A])(f: A => F[B]): F[B]
  extension [A] (fa: F[A]) def flatmap[B](f: A => F[B]): F[B] = flatmap2(fa)(f)
