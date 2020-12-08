import scala.annotation.tailrec
import scala.io.Source

object Main extends App {
  type Operations = IndexedSeq[(String, Int)]
  val input = Source.fromFile("Day8/input.txt")
  val operations = parseInput(input)
  println(part1(operations))
  println(part2(operations))

  def part1(operations: Operations): Option[Int] = {
    @tailrec
    def iterateOperations(currIndex: Int, acc: Int, visitedOps: List[Int]): Option[Int] = {
      val updatedOperations = currIndex :: visitedOps

      operations.lift(currIndex) match {
        case Some(("acc", amount)) => iterateOperations(currIndex + 1, acc + amount, updatedOperations)
        case Some(("jmp", amount)) if !visitedOps.contains(currIndex + amount) =>
          iterateOperations(currIndex + amount, acc, updatedOperations)
        case Some(("jmp", _)) => Some(acc)
        case Some(("nop", _)) => iterateOperations(currIndex + 1, acc, updatedOperations)
        case _ => None
      }
    }

    iterateOperations(0, 0, List())
  }

  def part2(operations: Operations) = {
    @tailrec
    def iterateOperations(ops: Operations, currIndex: Int, acc: Int, visitedOps: List[Int]): Option[Int] = {
      val updatedOperations = currIndex :: visitedOps

      ops.lift(currIndex) match {
        case Some(("acc", amount)) => iterateOperations(ops, currIndex + 1, acc + amount, updatedOperations)
        case Some(("jmp", amount)) if !visitedOps.contains(currIndex + amount) =>
          iterateOperations(ops, currIndex + amount, acc, updatedOperations)
        case Some(("jmp", _)) => None
        case Some(("nop", _)) => iterateOperations(ops, currIndex + 1, acc, updatedOperations)
        case _ if currIndex < 0 => None
        case _ => Some(acc)
      }
    }

    def swap(op: String): String = if (op == "jmp") "nop" else "jmp"

    operations
      .filter { case (op, _) => op == "jmp" || op == "nop" }
      .zipWithIndex
      .flatMap { case ((op, count), index) =>
        iterateOperations(operations.updated(index, (swap(op), count)), 0, 0, List())
      }
  }


  def parseInput(input: Source): Operations = {
    val lineRegex = """(.*?) ([+]|-)(\d*)""".r

    input.getLines().flatMap {
      case lineRegex(op, sign, number) => Some(op, if (sign == "+") number.toInt else 0 - number.toInt)
      case _ => None
    }.toIndexedSeq
  }
}
