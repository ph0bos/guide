package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService
import scala.concurrent.ExecutionContext

class EpisodeResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Gets a specific Episode by identifier.
   */
  get("/:id") {
    new AsyncResult() {
      val is = GuideService.getEpisode(params("id"))
    }
  }

  /**
   * Gets a specific Episode cast list by identifier.
   */
  get("/:id/cast") {
    new AsyncResult() {
      val is = GuideService.getEpisodeCast(params("id"))
    }
  }

  /**
   * Gets a specific Episode crew list by identifier.
   */
  get("/:id/crew") {
    new AsyncResult() {
      val is = GuideService.getEpisodeCrew(params("id"))
    }
  }

  before() {
    contentType = formats("json")
  }
}
