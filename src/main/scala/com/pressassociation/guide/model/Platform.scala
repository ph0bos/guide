package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Platform (
  @Key("id")
  id: String,
  name: Option[String],
  region: Seq[Region] = Seq.empty
)

object PlatformDAO extends SalatDAO[Platform, String](collection = MongoDBSetup.mongoDB("platform"))