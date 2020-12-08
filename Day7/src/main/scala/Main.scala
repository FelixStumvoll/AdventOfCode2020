import scala.io.Source

object Main extends App {
  type BagMap = Map[String, Map[String, Int]]
  val input = Source.fromFile("Day7/input.txt")
  val bags = parseBags(input.getLines().toList)

  println(part1(bags))
  println(part2(bags))

  def part1(bagMap: BagMap): Int = {
    def contains(bag: String): Set[String] =
      bagMap.get(bag) match {
        case Some(sub) =>
          val subResult = sub.keySet.flatMap(contains)
          if (subResult.isEmpty && !sub.contains("shiny gold")) subResult else subResult + bag
        case None => Set.empty
      }

    bagMap.keySet.flatMap(contains).size
  }

  def part2(bagMap: BagMap): Int = {
    def countSubBags(bag: String): Int =
      bagMap.get(bag) match {
        case Some(sub) => sub.map { case (color, count) => count + count * countSubBags(color) }.sum
        case None => 0
      }

    countSubBags("shiny gold")
  }

  def parseBags(lines: Seq[String]): BagMap = {
    val bagRegex = """(.*?) bags contain (.*).""".r
    val subBagRegex = """(\d)\s(.*?)\sbag(?>s|)""".r

    lines.flatMap(line => line match {
      case bagRegex(color, subBag) =>
        Some(color -> subBagRegex
          .findAllIn(subBag)
          .flatMap {
            case subBagRegex(count, color) => Some(color -> count.toInt)
            case _ => None
          }.toMap)
      case _ => None
    }).toMap
  }
}
