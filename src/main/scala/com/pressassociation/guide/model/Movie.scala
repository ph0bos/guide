package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.novus.salat._
import com.mongodb.casbah.Imports._

case class Movie (
  @Key("id")
  id: String,
  title: String,
  year: Option[Int],
  rating: Option[Int],
  certificate: Option[String],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String],
  image: Option[Seq[String]]
)

object MovieDAO extends SalatDAO[Movie, String](collection = MongoDBSetup.mongoDB("movie")) {

  def getCast(id: String): List[Person] = {
    (collection.distinct("cast", MongoDBObject("id" -> id)).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  def getCrew(id: String): List[Person] = {
    (collection.distinct("crew", MongoDBObject("id" -> id)).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  def getDistinctCategories(): List[Any] = {
    (collection.distinct("category").toList)
  }
}
