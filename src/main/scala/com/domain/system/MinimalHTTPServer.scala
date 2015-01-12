package com.domain.system

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import org.jboss.netty.handler.codec.http._

object MinimalHTTPServer extends App {

  val service = new Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = Future.value(
      new DefaultHttpResponse(request.getProtocolVersion, HttpResponseStatus.OK)
    )
  }

  val server = Http.serve(":8080", service)
  Await.ready(server)
}