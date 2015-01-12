package com.domain.system

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import com.twitter.io.Charsets

import org.jboss.netty.handler.codec.http._
import org.jboss.netty.buffer.ChannelBuffers


object MinimalHTTPServerTwo extends App {

  val service = new Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = {
      val response = new DefaultHttpResponse(request.getProtocolVersion, HttpResponseStatus.OK)
      response.setContent(ChannelBuffers.copiedBuffer("Thanks", Charsets.Utf8))
      Future.value(response)
    }
  }

  val server = Http.serve(":8080", service)
  Await.ready(server)
}