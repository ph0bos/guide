package com.pressassociation.guide.model

case class Channel (
  id: String,
  title: String,
  epg: Option[Seq[Epg]]
)
