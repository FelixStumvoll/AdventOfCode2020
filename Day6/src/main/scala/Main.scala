import scala.io.Source

object Main extends App {
  val questions = parseInput(Source.fromFile("Day6/input.txt"))
  println(part1(questions))
  println(part2(questions))

  def part1(questions: Seq[String]): Int =
    questions.map(q => countDistinct(q.replace("\n", ""))).sum

  def part2(questions: Seq[String]): Int =
    questions.map(q => countDistinct(q.filter(c => q.split("\n").forall(_.contains(c))))).sum

  def countDistinct(seq: Seq[_]): Int = seq.toSet.size

  def parseInput(source: Source): Seq[String] =
    source.getLines().mkString("\n").split("\n\n")
}