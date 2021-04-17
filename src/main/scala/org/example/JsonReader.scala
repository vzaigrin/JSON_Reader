package org.example

import io.circe
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import io.circe.generic.auto._
import io.circe.parser.decode

object JsonReader {
  case class Wine(
      id: Option[Int],
      country: Option[String],
      points: Option[Int],
      price: Option[Int],
      title: Option[String],
      variety: Option[String],
      winery: Option[String]
  )

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: Json_Reader <path-to-file>")
      sys.exit(-1)
    }

    val conf: SparkConf = new SparkConf().setAppName("JsonReader")
    val sc: SparkContext = new SparkContext(conf)

    val jsonFile: RDD[String] =
      try sc.textFile(args.head)
      catch {
        case e: Exception =>
          println(s"ERROR reading json file: ${e.getMessage}\n")
          sc.stop()
          sys.exit(-1)
      }

    jsonFile
      .map(decode[Wine](_))
      .foreach {
        case Right(v) => println(v)
        case Left(v)  => println(s"Error: $v")
      }

    sc.stop()
  }
}
