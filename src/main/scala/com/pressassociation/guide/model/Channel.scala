package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Channel (
  @Key("id")
  id: String,
  name: Option[String],
  number: Option[Int],
  image: Option[String],
  url: Option[String],
  mediaType: Option[String]
)

object ChannelDAO extends SalatDAO[Channel, String](collection = MongoDBSetup.mongoDB("channel"))