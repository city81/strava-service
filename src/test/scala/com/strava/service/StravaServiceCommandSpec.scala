package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain.Activity
import org.scalatest.Ignore
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.time.{Second, Span}
import scala.concurrent.ExecutionContext.Implicits.global


class StravaServiceCommandSpec extends UnitSpec with WireMockFixture {

  import com.strava.JsonFormats._

  implicit val system = ActorSystem("on-spray-can")

  val service = new StravaServiceCommand(Configuration(
    baseUrl = "http://localhost:8080/",
    appToken = "testAppToken"
  ))

  "The StravaServiceCommand when sending http request" should {

    "add the application token to http header" in {

      val requests = addRequestHeaderListener()

      val requestUrl = "activities/12345"
      service.makeAPIRequest[Activity](requestUrl)

//      val requestUrl = "segments/explore"
//      service.makeAPIRequest[Activity](requestUrl, Some(Map("bounds" -> "37.821362,-122.505373,37.842038,-122.465977")))

      val timeout = org.scalatest.concurrent.Eventually.PatienceConfig(Span(1, Second))
      eventually(requests should have length(1))(timeout)
      val appToken = requests(0).getHeader("Authorization")
      appToken.firstValue() should be("Bearer testAppToken")
    }


  }

}