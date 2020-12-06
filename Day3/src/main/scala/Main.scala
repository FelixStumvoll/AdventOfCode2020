import scala.io.Source

sealed class MapField

case object Tree extends MapField

case object Empty extends MapField

object Main extends App {
  type GameMap = Seq[Seq[MapField]]

  val inputFile = Source.fromFile("./Day3/input.txt")
  val map = parseFile(inputFile)

  def countTrees(path: Seq[MapField]): BigInt = path.count(_ == Tree)

  val pathFunc = traverseMap(map, 0, 0) _

  println(countTrees(pathFunc(1, 3)))

  val toCheck = List((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))
  println(toCheck.map(t => countTrees(pathFunc(t._1, t._2))).product)

  def parseFile(input: Source): GameMap = {
    input.getLines()
      .map(_.split("")
        .map(symbol => if (symbol == "#") Tree else Empty)
        .toList)
      .toList
  }

  def traverseMap(map: GameMap, x: Int, y: Int)(moveX: Int, moveY: Int): List[MapField] = {
    map.lift(x) match {
      case Some(row) => row.lift(y) match {
        case Some(field) => field :: traverseMap(map, x + moveX, y + moveY)(moveX, moveY)
        case None => traverseMap(map, x, y - row.size)(moveX, moveY)
      }
      case None => Nil
    }
  }
}
