    
given Monad[List] with
  def pure[A](x: A): List[A] =
    List(x)
  def fmap[A, B](fa: List[A])(f: A => B): List[B] =
    fa.map(f)
  def flatmap2[A, B](fa: List[A])(f: A => List[B]): List[B] =
    fa.flatMap(f)
    