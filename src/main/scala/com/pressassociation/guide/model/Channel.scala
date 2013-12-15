package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Channel (
  @Key("id")
  id: String,
  name: Option[String],
  number: Option[Int],
  image: Option[String],
  url: Option[String],
  mediaType: Option[String]
)