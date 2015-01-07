package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain.Activity
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.time.{Second, Span}
import scala.concurrent.ExecutionContext.Implicits.global

class StravaServiceCommandSpec extends UnitSpec with WireMockFixture {

  import com.strava.JsonFormats._

  implicit val system = ActorSystem("on-spray-can")

  "StravaServiceCommand sending http request" should {

    "add the application token to http header to all requests" in {

      val service = new StravaServiceCommand(Configuration(
        baseUrl = "http://localhost:8080/",
        appToken = "testAppToken"
      ))

      val requests = addRequestHeaderListener()

      val requestUrl = "activities/12345"

      service.makeAPIRequest[Activity](requestUrl)

      val timeout = org.scalatest.concurrent.Eventually.PatienceConfig(Span(1, Second))
      eventually(requests should have length(1))(timeout)
      val appToken = requests(0).getHeader("Authorization")
      appToken.firstValue() should be("Bearer testAppToken")
    }
  }

}