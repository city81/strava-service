package com.strava.service

import akka.actor.ActorSystem
import com.strava.Configuration
import com.typesafe.config.ConfigFactory
import org.scalatest.Ignore

import scala.util.{Failure, Success}

@Ignore
object ExampleStravaService extends App {

  override def main(args: Array[String]) {

    implicit val system = ActorSystem()
    import system.dispatcher
    val conf = ConfigFactory.load()
    val baseUrl = conf.getString("stravaService.baseUrl")
    val appToken = conf.getString("stravaService.appToken")

    val config = new Configuration(baseUrl, appToken)
    val command = new StravaServiceCommand(config)

    val stravaService = new StravaService(config, command)

    val testSegmentId = 5395793
    val testSegmentEffortId = 5563767904L
    val testGearId = "b1737196"

    // retrieve a segment
    stravaService.retrieveSegment(testSegmentId) onComplete {
      case Success(Some(segment)) =>
        println("Segment is: " + segment)
      case Failure(error) =>
        println("error " + error)
    }

    // retrieve starred segments
    stravaService.retrieveStarredSegments() onComplete {
      case Success(Some(segments)) =>
        println("Segments are: " + segments)
      case Failure(error) =>
        println("error " + error)
    }

    // retrieve a segment effort
    stravaService.retrieveSegmentEffort(testSegmentEffortId) onComplete {
      case Success(Some(segmentEffort)) =>
        println("Segment Effort is: " + segmentEffort)

        // retrieve an activity from a segment effort
        segmentEffort.activity match {
          case Some(activity) => {
            stravaService.retrieveActivity(activity.id) onComplete {
              case Success(Some(activity)) =>
                println("Activity is: " + activity)
              case Failure(error) =>
                println("error " + error)
            }
          }
          case None => println("no activity")
        }

        // retrieve an athlete from a segment effort
        segmentEffort.athlete match {
          case Some(athlete) => {
            stravaService.retrieveAthlete(athlete.id) onComplete {
              case Success(Some(athlete)) =>
                println("Athlete is: " + athlete)
              case Failure(error) =>
                println("error " + error)
            }
          }
          case None => println("no athlete")
        }

      case Failure(error) =>
        println("error " + error)
    }

    // retrieve gear
    stravaService.retrieveGear(testGearId) onComplete {
      case Success(Some(gear)) =>
        println("Gear is: " + gear)
      case Failure(error) =>
        println("error " + error)
    }

  }

}