import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val inputFile = Source.fromFile("./Day1/input.txt")
    val allNumbers = inputFile.getLines().map(_.toInt).toList
    println(part1(allNumbers))
    println(part2(allNumbers))

    inputFile.close()
  }

  def part1(numbers: Seq[Int]): Option[Int] =
    (for {
      n1 <- numbers
      n2 <- numbers
      if n1 + n2 == 2020
    } yield n1 * n2).headOption

  def part2(numbers: Seq[Int]): Option[Int] =
    (for {
      n1 <- numbers
      n2 <- numbers
      n3 <- numbers
      if n1 + n2 + n3 == 2020
    } yield n1 * n2 * n3)
      .headOption
}