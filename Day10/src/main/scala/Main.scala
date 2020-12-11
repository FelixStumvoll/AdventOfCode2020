import scala.io.Source

object Main extends App {
  var input = Source.fromFile("Day10/input.txt")
  val numbers = input.getLines().map(_.toInt).toList
  val correctNums = ((numbers.max + 3) :: 0 :: numbers).sorted
  val diffs = part1(correctNums)
  println(diffs)
  println(part2(correctNums))

  def part1(numbers: Seq[Int]): Int = {
    val diffs = numbers
      .foldLeft((Map(), 0): (Map[Int, Int], Int)) { case ((map, last), current) =>
        val joltDif = current - last
        (map.updated(joltDif, map.getOrElse(joltDif, 0) + 1), current)
      }._1

    diffs.getOrElse(1, 0) * diffs.getOrElse(3, 0)
  }

  def part2(numbers: Seq[Int]) = {
    val res = numbers.zipWithIndex.foldLeft(Map(0 -> BigInt(1)): Map[Int, BigInt]) {
      case (map, (number, i)) =>
        (1 to 3).foldLeft(map) { case (m, j) =>
          numbers.lift(i - j) match {
            case Some(num) if number - num <= 3 => m.updated(i, m.getOrElse(i, BigInt(0)) + m.getOrElse(i - j, BigInt(0)))
            case _ => m
          }
        }
    }

    res.get(numbers.size - 1)
  }
}
