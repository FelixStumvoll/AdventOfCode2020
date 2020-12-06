import scala.io.Source

object Main extends App {
  val inputFile = Source.fromFile("./Day5/input.txt")
  val seats = parseFile(inputFile).map(parseSeat)

  val seatIds = part1(seats)
  println(seatIds.max)
  println(part2(seatIds).headOption)

  def part1(seats: Seq[(Int, Int)]): Seq[Int] = seats.map(seat => seat._1 * 8 + seat._2)

  def part2(seats: Seq[Int]): Seq[Int] =
    (for {
      row <- 0 to 127
      column <- 0 to 7
    } yield row * 8 + column).filterNot(seats.contains).filter(s => seats.contains(s + 1) && seats.contains(s - 1))

  def parseSeat(seat: (String, String)): (Int, Int) = {
    val substitutions = Map('F' -> '0', 'B' -> '1', 'L' -> '0', 'R' -> '1')

    def fromBinary(string: String) = Integer.parseInt(string.flatMap(substitutions.get).mkString, 2)

    (fromBinary(seat._1), fromBinary(seat._2))
  }

  def parseFile(source: Source): Seq[(String, String)] = source.getLines().map(_.splitAt(7)).toList
}