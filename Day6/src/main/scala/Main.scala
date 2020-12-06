import scala.io.Source

object Main extends App {
  val questions = parseInput(Source.fromFile("Day6/input.txt"))
  println(part1(questions))
  println(part2(questions))

  def part1(questions: Seq[String]): Int =
    questions.map(q => q.replace("\n", "").toSet.size).sum

  def part2(questions: Seq[String]): Int =
    questions.map(_.split("\n").reduce(_.intersect(_)).length).sum

  def parseInput(source: Source): Seq[String] = source.getLines().mkString("\n").split("\n\n")
}