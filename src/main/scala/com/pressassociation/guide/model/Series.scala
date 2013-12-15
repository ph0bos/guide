package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.novus.salat._

case class Series (
  @Key("id")
  id: String,
  title: String,
  genre: Option[String],
  category: Option[Seq[String]],
  synopsis: Option[String],
  summary: Option[String],
  web: Option[Seq[String]],
  twitter: Option[Seq[String]],
  image: Option[Seq[String]]
)

object SeriesDAO extends SalatDAO[Series, String](collection = MongoDBSetup.mongoDB("series")) {

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
