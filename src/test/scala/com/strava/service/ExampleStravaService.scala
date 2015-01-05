package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.strava.domain._
import com.strava.service.StravaService
import com.typesafe.config.ConfigFactory
import org.joda.time.DateTime
import scala.collection.immutable.{HashSet, HashMap}
import scala.concurrent._
import scala.util.{Failure, Success}


object ExampleStravaService extends App {

  override def main(args: Array[String]) {

    implicit val system = ActorSystem()
    import system.dispatcher
    val conf = ConfigFactory.load()
    val appToken = conf.getString("stravaService.appToken")

    val config = new Configuration(appToken)
    val command = new StravaServiceCommand(config)

    val stravaService = new StravaService(config, command)

    // retrieve a segment effort
    stravaService.retrieveSegmentEffort("5563767904") onComplete {
      case Success(Some(segmentEffort)) =>
        println("Segment Effort is: " + segmentEffort)
      case Failure(error) =>
        println("error " + error)
    }

//    // retrieve a segment
//    stravaService.retrieveSegment("5395793") onComplete {
//      case Success(Some(segment)) =>
//        println("Segment is: " + segment)
//      case Failure(error) =>
//        println("error " + error)
//    }

  }

}