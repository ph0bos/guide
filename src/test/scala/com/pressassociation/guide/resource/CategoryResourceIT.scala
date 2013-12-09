package com.pressassociation.guide.resource

import akka.actor.ActorSystem
import org.scalatra.test.scalatest.{ScalatraSuite}
import org.scalatest.FunSuite

/**
 * Created by stevenr on 03/12/2013.
 */
class CategoryResourceIT extends ScalatraSuite with FunSuite {

  addServlet(new CategoryResource, "/*")

  test("get category list") {
    get("/") {
      status should equal (200)
    }
  }
}
