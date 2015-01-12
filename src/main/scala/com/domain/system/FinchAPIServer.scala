import com.twitter.finagle.httpx.Method
import com.twitter.finagle.httpx.path._
import com.twitter.finagle.{Httpx, Service}
import com.twitter.util.{Future, Await}

import io.finch._
import io.finch.json.Json
import io.finch.response._

object FinchAPIServer extends App {

  val service = new Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest): Future[HttpResponse] = {
      Ok(
        Json.obj(
          "id" -> "123",
          "title" -> "Charlie Sheen continues his epic rant against Kim Kardashian... then apologizes",
          "copy" -> "Dear Kim, my extreme bad. really embarrassed by my actions. I was already pissed about some other crap that had nothing to do with you. I heard a story that bothered me. wrote some trash you didn't deserve. Ever. I'm an idiot as often as I'm a genius. that day, clearly I was the former. xox #ShutUpSheen",
          "byline" -> "TMZ"
        )
      ).toFuture
    }
  }

  val endpoint = Endpoint[HttpRequest, HttpResponse] {
    case Method.Get -> Root / "api" => service
  }

  val server = Httpx.serve(":8080", endpoint.toService)
  Await.ready(server)

}
