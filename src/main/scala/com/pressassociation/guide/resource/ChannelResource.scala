package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import com.pressassociation.guide.service.GuideService
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import scala.concurrent.ExecutionContext
import org.joda.time.DateTime
import com.pressassociation.guide.util.DateTimeUtil

class ChannelResource(system: ActorSystem) extends ScalatraServlet
  with FutureSupport with JacksonJsonSupport with CorsSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Get a list of available Channels.
   */
  get("/") {
      new AsyncResult() {
         val is =
           if (params.contains("platformId") && params.contains("regionId")) {
             GuideService.getChannelList(params("platformId"), params("regionId"))
           } else {
             GuideService.getChannelList
           }
       }
  }

  /**
   * Get a specific Channel by identifier.
   */
  get("/:id") {
    new AsyncResult() {
      val is = GuideService.getChannel(params("id"))
    }
  }

  /**
   * Get a schedule for a specific Channel by identifier.
   */
  get("/:id/schedule") {
    var start : DateTime = DateTime.now.minusHours(1)
    var end : DateTime = DateTime.now.plusHours(12)

    if (params.contains("start")) start = DateTimeUtil.parseString(params("start"))
    if (params.contains("end")) end = DateTimeUtil.parseString(params("end"))

    new AsyncResult() {
      val is = GuideService.getScheduledProgrammeList(params("id"), null, null, null, start, end)
    }
  }

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

  before() {
    contentType = formats("json")
  }
}
