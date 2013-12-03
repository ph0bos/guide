package com.pressassociation.guide.model

case class Person (
  id: String,
  name: String,
  role: Option[String],
  knownFor: Option[Seq[KnownFor]]
)
