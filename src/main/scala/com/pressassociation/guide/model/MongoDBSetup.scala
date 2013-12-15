package com.pressassociation.guide.model

import com.typesafe.config.ConfigFactory
import com.mongodb.casbah.Imports._

object MongoDBSetup {
  val conf = ConfigFactory.load();
  val mongoDB = MongoClient(MongoClientURI(conf.getString("mongo.db.connection")))("guide")
}
