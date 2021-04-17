package org.example

import org.example.JsonReader.Wine
import org.scalatest.FunSuite
import io.circe.generic.auto._
import io.circe.parser.decode

class JsonTest extends FunSuite {
  test("JSON with all fields completed") {
    val json =
      """{"id":1,"country":"Portugal","points":87,"price":15.0,"title":"Quinta dos Avidagos 2011 Avidagos Red (Douro)","variety":"Portuguese Red","winery":"Quinta dos Avidagos"}"""
    assert(
      decode[Wine](json) ===
        Right(
          Wine(
            Some(1),
            Some("Portugal"),
            Some(87),
            Some(15),
            Some("Quinta dos Avidagos 2011 Avidagos Red (Douro)"),
            Some("Portuguese Red"),
            Some("Quinta dos Avidagos")
          )
        )
    )
  }

  test("JSON with some fields missing") {
    val json =
      """{"id":0,"country":"Italy","points":87,"title":"Nicosia 2013 Vulkà Bianco  (Etna)","variety":"White Blend","winery":"Nicosia"}"""
    assert(
      decode[Wine](json) ===
        Right(
          Wine(
            Some(0),
            Some("Italy"),
            Some(87),
            None,
            Some("Nicosia 2013 Vulkà Bianco  (Etna)"),
            Some("White Blend"),
            Some("Nicosia")
          )
        )
    )
  }
}
