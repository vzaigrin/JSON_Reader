name := "JSON_Reader"

version := "1.0"

scalaVersion := "2.12.12"

lazy val sparkVersion = "3.0.1"
lazy val json4sVersion = "3.6.11"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
