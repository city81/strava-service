package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain._

import scala.concurrent._
import scala.language.postfixOps

final class StravaService(val command: StravaServiceCommand)
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

  // TODO add params
  def retrieveAllSegmentEfforts(id: Long): Future[Option[List[SegmentEffort]]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segments/" + id.toString + "/all_efforts"
    command.makeAPIRequest[List[SegmentEffort]](requestUrl)
  }

  // TODO add params
  def retrieveSegmentLeaderboard(id: Long): Future[Option[SegmentLeaderboard]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segments/" + id.toString + "/leaderboard"
    command.makeAPIRequest[SegmentLeaderboard](requestUrl)
  }

  // TODO add pagination params
  def retrieveStarredSegments(): Future[Option[List[Segment]]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segments/starred"
    command.makeAPIRequest[List[Segment]](requestUrl)
  }

  // TODO add optional params
  def exploreSegments(requestBody: Option[Map[String,String]]): Future[Option[SegmentsForGivenArea]] = {

    import com.strava.JsonFormats._

    val requestUrl = "segments/explore"
    command.makeAPIRequest[SegmentsForGivenArea](requestUrl, requestBody)
  }

  def retrieveActivity(id: Long): Future[Option[Activity]] = {

    import com.strava.JsonFormats._

    val requestUrl = "activities/" + id.toString
    command.makeAPIRequest[Activity](requestUrl)
  }

  def retrieveFriendsActivities: Future[Option[List[Activity]]] = {

    import com.strava.JsonFormats._

    val requestUrl = "activities/following"
    command.makeAPIRequest[List[Activity]](requestUrl)
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