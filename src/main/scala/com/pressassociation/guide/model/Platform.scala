package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Platform (
  @Key("id")
  id: String,
  name: Option[String],
  region: Seq[Region] = Seq.empty
)