name := "JSON_Reader"

version := "1.0"

scalaVersion := "2.12.12"

lazy val sparkVersion = "3.0.1"
lazy val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
