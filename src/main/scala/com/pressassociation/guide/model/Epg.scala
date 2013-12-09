package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Epg (
  @Key("id")
  id: String,
  name: String,
  number: Int
)
