package org.example

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization
import org.json4s.{Formats, FullTypeHints}

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

  implicit val formats: AnyRef with Formats = {
    Serialization.formats(FullTypeHints(List(classOf[Wine])))
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: Json_Reader <path_to_file>")
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
      .map(parse(_).extract[Wine])
      .foreach(println)

    sc.stop()
  }
}
