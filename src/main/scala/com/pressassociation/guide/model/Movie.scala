package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Movie (
  @Key("id")
  id: String,
  title: String,
  year: Option[Int],
  rating: Option[Int],
  certificate: Option[String],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String]
)


