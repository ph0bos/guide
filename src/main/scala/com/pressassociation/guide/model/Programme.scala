package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Programme (
  @Key("id")
  id: String,
  dateTime: Option[java.util.Date],
  title: String,
  duration: Int,
  category: Seq[String],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String],
  stereo: Boolean,
  subtitles: Boolean,
  surround: Boolean,
  widescreen: Boolean,
  hd: Boolean,
  `3d`: Boolean,
  repeat: Boolean,
  premiere: Boolean,
  channel: Channel,
  movie: Option[Movie],
  series: Option[Series],
  season: Option[Season],
  episode: Option[Episode],
  image: Option[Seq[String]]
)

object ProgrammeDAO extends SalatDAO[Programme, String](collection = MongoDBSetup.mongoDB("programme"))
