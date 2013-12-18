package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Epg (
  @Key("id")
  id: String,
  name: String,
  mediaType: Option[String],
  number: Int,
  image: Option[String],
  url: Option[String]
)

object EpgDAO extends SalatDAO[Epg, String](collection = MongoDBSetup.mongoDB("epg"))
