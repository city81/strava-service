package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain.{Gear, Polyline, Segment}
import net.liftweb.json.DefaultFormats
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent._
import org.scalatest.{FlatSpec, ShouldMatchers}
import spray.httpx.unmarshalling._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class StravaServiceSpec extends FlatSpec with ShouldMatchers with MockFactory with ScalaFutures {

  implicit val system = ActorSystem("on-spray-can")

  // TODO make config implicit to avoid this workaround
  class MockCommand extends StravaServiceCommand(Configuration(
    baseUrl = "http://localhost:8080/",
    appToken = "testAppToken"
  ))

  val testCommand = mock[MockCommand]


  "The StravaServiceCommand" should "retrieve gear based on the supplied id" in {

    val testGearId = "b12345"
    val gearResponseSuccess = Gear(id = testGearId,
      primary = true,
      name = "name",
      distance = 10,
      resource_state = 1)

    implicit val liftJsonFormats = DefaultFormats

    (testCommand.makeAPIRequest(_: String, _: Option[Map[String, String]])(_: FromResponseUnmarshaller[Gear])).expects("gear/" + testGearId, None, *).returning(Future.successful(Some(gearResponseSuccess)))

    val testService = new StravaService(testCommand)

    val result = testService.retrieveGear(testGearId)

    whenReady(result) { res =>
      res match {
        case Some(gear) =>
          gear.id should be(testGearId)
          gear.primary shouldBe true
          gear.name should be("name")
          gear.distance should be(10)
          gear.resource_state should be(1)
        case _ =>
          fail()
      }
    }
  }

  it should "retrieve a segment based on the supplied id" in {

    val testSegmentId = 5395793
    val segmentResponseSuccess = Segment(id = testSegmentId,
      resource_state = 3,
      name = "Making Life Taste Better",
      activity_type = "Ride",
      distance = 122.5.toFloat,
      average_grade = 0.1.toFloat,
      maximum_grade = 0.6.toFloat,
      elevation_high = 8.0.toFloat,
      elevation_low = 7.7.toFloat,
      start_latlng = Set(51.621334.toFloat, -3.935336.toFloat),
      end_latlng = Set(51.620365.toFloat, -3.934507.toFloat),
      climb_category = 0,
      city = "Swansea",
      state = "Wales",
      `private` = Some(false),
      created_at = Some("2013-09-05T18:28:31Z"),
      updated_at = Some("2014-12-23T14:03:06Z"),
      total_elevation_gain = Some(0.0.toFloat),
      map = Some(Polyline("s5395793", Some("ihazHzr_WZOVS`BuAj@k@"), None, 3)),
      effort_count = Some(2318),
      athlete_count = Some(506),
      hazardous = Some(false),
      pr_time = None,
      pr_distance = None,
      starred = false,
      climb_category_desc = None)

    implicit val liftJsonFormats = DefaultFormats

    (testCommand.makeAPIRequest(_: String, _: Option[Map[String, String]])(_: FromResponseUnmarshaller[Segment])).expects("segments/" + testSegmentId, None, *).returning(Future.successful(Some(segmentResponseSuccess)))

    val testService = new StravaService(testCommand)

    val result = testService.retrieveSegment(testSegmentId)

    whenReady(result) { res =>
      res match {
        case Some(segment) =>
          segment.id should be(testSegmentId)
          segment.city should be("Swansea")
          segment.state should be("Wales")
          // TODO add the remainder
        case _ =>
          fail()
      }
    }
  }

}