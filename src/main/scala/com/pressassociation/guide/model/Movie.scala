package com.pressassociation.guide.model

case class Movie (
  id: String,
  title: String,
  year: Option[Int],
  rating: Option[Int],
  certificate: Option[String],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String]
)
