package com.pressassociation.guide.model

import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._

case class Person (
  @Key("id")
  id: String,
  name: String,
  role: Option[String]
)

object PersonDAO extends SalatDAO[Person, String](collection = MongoDBSetup.mongoDB("person"))

