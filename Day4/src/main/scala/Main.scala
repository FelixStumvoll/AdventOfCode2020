import scala.io.Source

object Main extends App {
  type Passport = Map[String, String]

  val requiredFields = List("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  val inputFile = Source.fromFile("./Day4/input.txt")
  println(parsePassports(inputFile)
    .map(parseSinglePassport)
    .count(p => validatePassportRules(p) && requiredFields.forall(p.keys.toList.contains(_))))

  inputFile.close()

  def parsePassports(inputFile: Source): Seq[String] =
    inputFile.getLines().mkString("\n").split("\n\n")

  def parseSinglePassport(passport: String): Passport =
    passport.replace("\n", " ")
      .split(" ")
      .map(p => p.split(":").toList)
      .flatMap {
        case "cid" :: _ => None
        case k :: v :: Nil => Some((k, v))
        case _ => None
      }
      .toMap

  def validatePassportRules(passport: Passport): Boolean =
    passport.forall {
      case ("byr", byr) => byr.length == 4 && (1920 to 2002).contains(byr.toInt)
      case ("iyr", iyr) => iyr.length == 4 && (2010 to 2020).contains(iyr.toInt)
      case ("eyr", eyr) => eyr.length == 4 && (2020 to 2030).contains(eyr.toInt)
      case ("hgt", hgt)
        if hgt.contains("cm") => (150 to 193).contains(hgt.replace("cm", "").toInt)
      case ("hgt", hgt)
        if hgt.contains("in") => (59 to 76).contains(hgt.replace("in", "").toInt)
      case ("hcl", hcl) => hcl.matches("#[0-9a-f]{6}")
      case ("ecl", ecl) => List("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl)
      case ("pid", pid) => pid.matches("[0-9]{9}")
      case _ => false
    }
}
