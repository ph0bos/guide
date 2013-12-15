package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Region (
  @Key("id")
  id: Option[String],
  name: Option[String]
)
