package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import spray.client.pipelining._
import spray.http.{HttpResponse, StatusCodes}
import spray.httpx.unmarshalling.FromResponseUnmarshaller

import scala.concurrent._
import scala.language.postfixOps

final class StravaServiceCommand(val config: Configuration)
                                (implicit executionContext: ExecutionContext, system: ActorSystem) {

  private def checkStatusCodeAndUnmarshal[T](implicit unmarshaller: FromResponseUnmarshaller[T]): Future[HttpResponse] => Future[Option[T]] =
    (futRes: Future[HttpResponse]) => futRes.map {
      res =>
        if (res.status == StatusCodes.OK) {
          println(res)
          Some(unmarshal[T](unmarshaller)(res))
        } else None
    }

  def makeAPIRequest[T](requestUrl: String) //, requestBody: Option[RequestBody] = None)
                       (implicit unmarshaller: FromResponseUnmarshaller[T]): Future[Option[T]] = {

    val pipeline = addHeader("Content-Type", "application/json") ~>
        addHeader("Accept", "application/json") ~>
        addHeader("Accept-Charset", "UTF-8") ~>
        addHeader("Authorization", "Bearer " + config.appToken) ~>
        sendReceive ~> checkStatusCodeAndUnmarshal[T]

    pipeline {
      Get(("https://www.strava.com/api/v3/" + requestUrl)) //, requestBody)
    }

  }

}