package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService
import com.pressassociation.guide.util.DateTimeUtil
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext

class ScheduleResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Get a list of scheduled Programmes.
   */
  get("/") {
    var start : DateTime = DateTime.now
    var end : DateTime = DateTime.now.plusHours(12)

    if (params.contains("start")) start = DateTimeUtil.parseString(params("start"))
    if (params.contains("end")) end = DateTimeUtil.parseString(params("end"))

    new AsyncResult() {
      val is = GuideService.getScheduledProgrammeList(params("channelId"), null, null, null, start, end)
    }
  }

  before() {
    contentType = formats("json")
  }
}
