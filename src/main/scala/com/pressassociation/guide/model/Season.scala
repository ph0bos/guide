package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Season (
  @Key("id")
  id: String,
  title: Option[String],
  number: Option[Int]
)
