package com.pressassociation.guide.model

import com.novus.salat.annotations.Key

case class Series (
  @Key("id")
  id: String,
  title: String,
  genre: Option[String],
  category: Option[Seq[String]],
  synopsis: Option[String],
  summary: Option[String],
  web: Option[Seq[String]],
  twitter: Option[Seq[String]],
  image: Option[Seq[String]]
)
