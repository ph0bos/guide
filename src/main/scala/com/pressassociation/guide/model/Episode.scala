package com.pressassociation.guide.model

case class Episode (
  id: String,
  title: Option[String],
  number: Option[Int],
  total: Option[Int],
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String]
)
