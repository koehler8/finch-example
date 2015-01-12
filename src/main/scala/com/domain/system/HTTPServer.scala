package com.domain.system

import com.twitter.finagle.http.{MediaType, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import com.twitter.io.Charsets

import org.jboss.netty.handler.codec.http._
import org.jboss.netty.buffer.ChannelBuffers

object HTTPServer extends App {

  val service = new Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = {
      val response = Response()
      response.setContentType(MediaType.Html, Charsets.Utf8.toString)
      response.setContent(ChannelBuffers.copiedBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>title</title><link rel=\"stylesheet\" href=\"style.css\"><script src=\"script.js\"></script></head><body><h1>Hello</h1></body></html>", Charsets.Utf8))
      Future.value(response)
    }
  }

  val server = Http.serve(":8080", service)
  Await.ready(server)
}