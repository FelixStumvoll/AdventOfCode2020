

name := "AdventOfCode2020"

version := "0.1"

scalaVersion := "2.13.4"


lazy val day1 = (project in file("Day1")).settings(
  name := "Day1",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day2 = (project in file("Day2")).settings(
  name := "Day2",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day3 = (project in file("Day3")).settings(
  name := "Day3",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day4 = (project in file("Day4")).settings(
  name := "Day4",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day5 = (project in file("Day5")).settings(
  name := "Day5",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day6 = (project in file("Day6")).settings(
  name := "Day6",
  mainClass in(Compile, run) := Some("Main"),
)

lazy val day7 = (project in file("Day7")).settings(
  name := "Day7",
  mainClass in(Compile, run) := Some("Main"),
)