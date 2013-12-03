package com.pressassociation.guide.resource

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class EpisodeResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit def executor: ExecutionContext = system.dispatcher
  protected implicit val jsonFormats: Formats = DefaultFormats

  /**
   * Gets a specific Episode by identifier.
   */
  get("/:id") {
    new AsyncResult {
      val is = GuideService.getEpisode(params("id"))
    }
  }

  /**
   * Gets a specific Episode cast list by identifier.
   */
  get("/:id/cast") {
    new AsyncResult {
      val is = GuideService.getEpisodeCast(params("id"))
    }
  }

  /**
   * Gets a specific Episode crew list by identifier.
   */
  get("/:id/crew") {
    new AsyncResult {
      val is = GuideService.getEpisodeCrew(params("id"))
    }
  }
}
