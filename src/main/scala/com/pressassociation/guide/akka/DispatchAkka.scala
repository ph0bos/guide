package com.pressassociation.guide.akka

import scala.concurrent.{Promise, Future, ExecutionContext}
import dispatch.{Req, as}

object DispatchAkka {

  def getData(request: Req)(implicit ctx: ExecutionContext): Future[String] = {
    val prom = Promise[String]()

    dispatch.Http(request OK as.String) onComplete {
      case r => prom.complete(r)
    }

    prom.future
  }
}