package com.pressassociation.guide.model

import com.novus.salat.annotations._

case class Person (
  @Key("id")
  id: String,
  name: String,
  role: Option[String]
)
