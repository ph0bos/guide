package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import com.pressassociation.guide.service.GuideService
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import scala.concurrent.ExecutionContext

class ChannelResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Get a list of available Channels.
   */
  get("/") {
    new AsyncResult() {
      val is = GuideService.getChannelList
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
   *
   * Note: Redirects to the designated Schedule resource.
   */
  get("/:id/schedule") {
    redirect("/schedule?channelId=%s&start=%s&end=%s"
      format (params("id"), params.getOrElse("start", "now.minus.1"), params.getOrElse("end", "now.plus.12")))
  }

  before() {
    contentType = formats("json")
  }
}
