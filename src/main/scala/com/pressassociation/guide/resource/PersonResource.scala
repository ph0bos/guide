package com.pressassociation.guide.resource

import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.pressassociation.guide.service.GuideService

class PersonResource(system: ActorSystem) extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit def executor: ExecutionContext = system.dispatcher
  protected implicit val jsonFormats: Formats = DefaultFormats

  /**
   * Gets a list of available Persons.
   */
  get("/") {
    if (!params.contains("search")) halt(400, "search query parameter is required")
    new AsyncResult {
      val is = GuideService.getPersonList(params("search"))
    }
  }

  /**
   * Gets a specific Person by identifier.
   */
  get("/:id") {
    new AsyncResult {
      val is = GuideService.getPerson(params("id"))
    }
  }
}
