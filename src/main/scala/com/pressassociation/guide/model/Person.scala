package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Person (
  @Key("id")
  id: String,
  name: String,
  firstName: Option[String],
  middleName: Option[String],
  lastName: Option[String],
  gender: Option[String],
  earlyLife: Option[String],
  career: Option[String],
  quote: Option[String],
  bestKnownFor: Option[String],
  trivia: Option[String],
  dob: Option[java.util.Date],
  pob: Option[String],
  image: Seq[String] = Seq.empty,
  role: Option[String]
)

object PersonDAO extends SalatDAO[Person, String](collection = MongoDBSetup.mongoDB("person"))

