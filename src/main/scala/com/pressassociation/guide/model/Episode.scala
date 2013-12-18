package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.novus.salat._

case class Episode (
  @Key("id")
  id: String,
  title: Option[String],
  number: Option[Int],
  total: Option[Int],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String],
  series: Option[Series],
  season: Option[Season],
  image: Option[Seq[String]]
)

object EpisodeDAO extends SalatDAO[Episode, String](collection = MongoDBSetup.mongoDB("episode")) {

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

  def getSeriesCast(id: String): List[Person] = {
    (collection.distinct("cast", MongoDBObject("series.id" -> id)).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  def getSeriesCrew(id: String): List[Person] = {
    (collection.distinct("crew", MongoDBObject("series.id" -> id)).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }
}
