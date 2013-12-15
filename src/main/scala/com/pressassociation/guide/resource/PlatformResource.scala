package com.pressassociation.guide.resource

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class PlatformResource(system: ActorSystem) extends ScalatraServlet
  with FutureSupport with JacksonJsonSupport with CorsSupport {

  protected implicit def jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Gets a list of available Platforms.
   */
  get("/") {
    new AsyncResult() {
      val is = GuideService.getPlatformList
    }
  }

  /**
   * Get a specific Platform by identifier.
   */
  get("/:id") {
    new AsyncResult() {
      val is = GuideService.getPlatform(params("id"))
    }
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

  before() {
    contentType = formats("json")
  }
}
