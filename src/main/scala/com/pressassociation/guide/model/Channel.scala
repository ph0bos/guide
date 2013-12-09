package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Channel (
  @Key("id")
  id: String,
  name: String,
  epg: Seq[Epg] = Seq.empty
)