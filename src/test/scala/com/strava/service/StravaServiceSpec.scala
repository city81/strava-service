package com.strava.service

import com.strava.Configuration
import net.liftweb.json.DefaultFormats
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.ActorSystem
import com.strava.domain.Gear
import org.scalatest.{FlatSpec, ShouldMatchers}
import org.scalamock.scalatest.MockFactory
import spray.httpx.unmarshalling._
import scala.concurrent.Future
import scala.util.{Failure, Success}
import org.scalatest.concurrent._

class StravaServiceSpec extends FlatSpec with ShouldMatchers with MockFactory with ScalaFutures {

  implicit val system = ActorSystem("on-spray-can")

  "The StravaServiceCommand" should "retrieve gear based on the supplied id" in {

    val testGearId = "b12345"
    val gearResponseSuccess = Gear(id = testGearId,
      primary = true,
      name = "name",
      distance = 10,
      resource_state = 1)

    // TODO make config implicit to avoid this workaround
    class MockCommand extends StravaServiceCommand(Configuration(
      baseUrl = "http://localhost:8080/",
      appToken = "testAppToken"
    ))
    val testCommand = mock[MockCommand]

    implicit val liftJsonFormats = DefaultFormats

    (testCommand.makeAPIRequest(_: String, _: Option[Map[String, String]])(_: FromResponseUnmarshaller[Gear])).expects("gear/" + testGearId, None, *).returning(Future.successful(Some(gearResponseSuccess)))

    val testService = new StravaService(testCommand)

    val result = testService.retrieveGear(testGearId)

    whenReady(result) { s =>
      s match {
        case Some(gear) =>
          gear.id should be("b12345")
          gear.primary shouldBe true
          gear.name should be("name")
          gear.distance should be(10)
          gear.resource_state should be(1)
        case _ =>
          fail()
      }
    }
  }

}