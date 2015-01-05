package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain._

import scala.concurrent._
import scala.language.postfixOps

final class StravaService(val config: Configuration, command: StravaServiceCommand)
                            (implicit executionContext: ExecutionContext, system: ActorSystem) {


  def retrieveSegmentEffort(id: String): Future[Option[SegmentEffort]] = {

    import spray.httpx.PlayJsonSupport._

    val requestUrl = "segment_efforts/" + id
    command.makeAPIRequest[SegmentEffort](requestUrl)
  }

  def retrieveSegment(id: String): Future[Option[Segment]] = {

    import spray.httpx.PlayJsonSupport._

    val requestUrl = "segments/" + id
    command.makeAPIRequest[Segment](requestUrl)
  }
}