package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain._

import scala.concurrent._
import scala.language.postfixOps

final class StravaService(val config: Configuration, command: StravaServiceCommand)
                         (implicit executionContext: ExecutionContext, system: ActorSystem) {


  def retrieveSegmentEffort(id: Long): Future[Option[SegmentEffort]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segment_efforts/" + id.toString
    command.makeAPIRequest[SegmentEffort](requestUrl)
  }

  def retrieveSegment(id: Long): Future[Option[Segment]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segments/" + id.toString
    command.makeAPIRequest[Segment](requestUrl)
  }

  def retrieveActivity(id: Long): Future[Option[Activity]] = {

    import com.strava.JsonFormats._

    val requestUrl = "activities/" + id.toString
    command.makeAPIRequest[Activity](requestUrl)
  }

  def retrieveAthlete(id: Long): Future[Option[Athlete]] = {

    import com.strava.JsonFormats._

    val requestUrl = "athletes/" + id.toString
    command.makeAPIRequest[Athlete](requestUrl)
  }

  def retrieveGear(id: String): Future[Option[Gear]] = {

    import com.strava.JsonFormats._

    val requestUrl = "gear/" + id
    command.makeAPIRequest[Gear](requestUrl)
  }

}