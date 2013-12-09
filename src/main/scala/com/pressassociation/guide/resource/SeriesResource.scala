package com.pressassociation.guide.resource

import _root_.akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService
import scala.concurrent.ExecutionContext

class SeriesResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit def executor: ExecutionContext = system.dispatcher

  /**
   * Gets a list of available Series.
   */
  get("/") {
    if (!params.contains("search")) halt(400, "search query parameter is required")
    GuideService.getSeriesList(params("search"))
  }

  /**
   * Gets a specific Series by identifier.
   */
  get("/:id") {
    new AsyncResult() {
      val is = GuideService.getSeries(params("id"))
    }
  }

  /**
   * Gets a specific Series season list by identifier.
   */
  get("/:id/season") {
    new AsyncResult() {
      val is = GuideService.getSeriesSeasonList(params("id"))
    }
  }

  /**
   * Gets a specific Series episode list by identifier.
   */
  get("/:id/episode") {
    new AsyncResult() {
      val is = GuideService.getSeriesEpisodeList(params("id"))
    }
  }

  /**
   * Gets a specific Series cast list by identifier.
   */
  get("/:id/cast") {
    new AsyncResult() {
      val is = GuideService.getSeriesCast(params("id"))
    }
  }

  /**
   * Gets a specific Series crew list by identifier.
   */
  get("/:id/crew") {
    new AsyncResult() {
      val is = GuideService.getSeriesCrew(params("id"))
    }
  }

  before() {
    contentType = formats("json")
  }
}
