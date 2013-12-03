package com.pressassociation.guide.resource

import scala.concurrent.{ExecutionContext}
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class ChannelResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit def executor: ExecutionContext = system.dispatcher
  protected implicit val jsonFormats: Formats = DefaultFormats

  /**
   * Get a list of available Channels.
   */
  get("/") {
    new AsyncResult {
      val is = GuideService.getChannelList()
    }
  }

  /**
   * Get a specific Channel by identifier.
   */
  get("/:id") {
    new AsyncResult {
      val is = GuideService.getChannel(params("id"))
    }
  }

  /**
   * Get a schedule for a specific Channel by identifier.
   *
   * Note: Redirects to the designated Schedule resource.
   */
  get("/:id/schedule") {
    redirect("/schedule?channelId=%s&start=%s&end=%s" format (params("id"), params("start"), params("end")))
  }
}
