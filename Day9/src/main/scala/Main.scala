import scala.annotation.tailrec
import scala.io.Source

object Main extends App {
  val input = Source.fromFile("Day9/input.txt")
  val numbers = input.getLines().map(BigInt(_)).toList

  val p1 = part1(numbers)
  println(p1)
  println(p1.flatMap(part2(numbers, _)))

  def part1(numbers: Seq[BigInt]): Option[BigInt] = {
    def checkRecursive(index: Int): Option[BigInt] =
      numbers.lift(index)
        .flatMap(current => {
          val checkPossible = numbers
            .slice(index - 25, index)
            .combinations(2)
            .map { case a :: b :: Nil => (a, b) }
            .exists { case (a, b) => a + b == current }
          if (checkPossible) checkRecursive(index + 1) else Some(current)
        })

    checkRecursive(25)
  }

  def part2(numbers: Seq[BigInt], number: BigInt) = {
    @tailrec
    def sizeRecursive(index: Int, size: Int): Option[Seq[BigInt]] = {
      if (size == numbers.size) None
      else if (numbers.slice(index, size).sum == number)
        Some(numbers.slice(index, size))
      else sizeRecursive(index, size + 1)
    }

    @tailrec
    def indexRecursive(index: Int): Option[Seq[BigInt]] = {
      sizeRecursive(index, 2) match {
        case Some(value) => Some(value)
        case _ => indexRecursive(index + 1)
      }
    }

    indexRecursive(1).map(l => l.min + l.max)
  }
}
