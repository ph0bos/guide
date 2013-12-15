package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService
import scala.concurrent.ExecutionContext
import org.joda.time.DateTime
import com.pressassociation.guide.util.DateTimeUtil

class PersonResource(system: ActorSystem) extends ScalatraServlet
  with FutureSupport with JacksonJsonSupport with CorsSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Gets a list of available Persons.
   */
  get("/") {
    if (!params.contains("search")) halt(400, "search query parameter is required")

    new AsyncResult() {
      val is = GuideService.getPersonList(params("search"))
    }
  }

  /**
   * Gets a specific Person by identifier.
   */
  get("/:id") {
    new AsyncResult() {
      val is = GuideService.getPerson(params("id"))
    }
  }

  /**
   * Get a schedule for a specific Person by identifier.
   */
  get("/:id/schedule") {
    var start : DateTime = DateTime.now.minusHours(1)
    var end : DateTime = DateTime.now.plusHours(12)

    if (params.contains("start")) start = DateTimeUtil.parseString(params("start"))
    if (params.contains("end")) end = DateTimeUtil.parseString(params("end"))

    new AsyncResult() {
      val is = GuideService.getScheduledProgrammeList(null, null, null, params("id"), start, end)
    }
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

  before() {
    contentType = formats("json")
  }
}
