import scala.io.Source

case class LineResult(lowerLimit: Int, upperLimit: Int, character: Char, password: String)

object Main {
  def main(args: Array[String]): Unit = {
    val inputFile = Source.fromFile("./Day2/input.txt")
    val lineResults = inputFile
      .getLines()
      .map(_.split("[- :]", 4).toList)
      .flatMap {
        case lower :: upper :: char :: password :: Nil => Some(LineResult(lower.toInt, upper.toInt, char(0), password))
        case _ => None
      }.toList
    println(part1(lineResults))
    println(part2(lineResults))

    inputFile.close()
  }

  def part1(lineResults: Seq[LineResult]): Int =
    lineResults
      .count { case LineResult(lower, upper, char, pw) => lower to upper contains pw.count(_ == char) }

  def part2(lineResults: Seq[LineResult]): Int =
    lineResults
      .count { case LineResult(lower, upper, char, pw) =>
        pw.length >= upper && (pw(lower) == char || pw(upper) == char) && (pw(lower) != pw(upper))
      }
}
