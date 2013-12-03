package com.pressassociation.guide.resource

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class MovieResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit def executor: ExecutionContext = system.dispatcher
  protected implicit val jsonFormats: Formats = DefaultFormats

  /**
   * Gets a list of available Movies.
   */
  get("/") {
    if (!params.contains("search")) halt(400, "search query parameter is required")
    new AsyncResult {
      val is = GuideService.getMovieList(params("search"))
    }
  }

  /**
   * Gets a specific Movie by identifier.
   */
  get("/:id") {
    new AsyncResult {
      val is = GuideService.getMovie(params("id"))
    }
  }

  /**
   * Gets a specific Movie cast by identifier.
   */
  get("/:id/cast") {
    new AsyncResult {
      val is = GuideService.getMovieCast(params("id"))
    }
  }

  /**
   * Gets a specific Movie crew by identifier.
   */
  get("/:id/crew") {
    new AsyncResult {
      val is = GuideService.getMovieCrew(params("id"))
    }
  }
}
