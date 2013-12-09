package com.pressassociation.guide.resource

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class CategoryResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit def jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Gets a list of available Categories.
   */
  get("/") {
    new AsyncResult() {
      val is = GuideService.getCategoryList
    }
  }

  before() {
    contentType = formats("json")
  }
}
