package org.example

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization
import org.json4s.{Formats, FullTypeHints}

object JsonReader extends App {
  case class User(id: Option[Int], country: Option[String], points: Option[Int], price: Option[Int], title: Option[String], variety: Option[String], winery: Option[String])
  implicit val formats: AnyRef with Formats = {
    Serialization.formats(FullTypeHints(List(classOf[User])))
  }

  val conf: SparkConf = new SparkConf().setAppName("JsonReader").setMaster("local[*]")
  val sc: SparkContext = new SparkContext(conf)

  val jsonFile: RDD[String] = try sc.textFile(args.head)
  catch {
    case e: Exception =>
      println(s"ERROR reading json file: ${e.getMessage}\n")
      sc.stop()
      sys.exit(-1)
  }

  jsonFile
    .map(parse(_).extract[User])
    .foreach { l =>
      println(l.toString)
    }

  sc.stop()
}
