package com.strava

import akka.actor.ActorSystem
import akka.event.Logging
import com.strava.service.StravaServiceCommand
import com.typesafe.config.ConfigFactory

object Boot extends App {

  val conf = ConfigFactory.load()

  val serverPort = conf.getInt("port")

  val baseUrl = conf.getString("stravaService.baseUrl")
  val appToken = conf.getString("stravaService.appToken")

  val configuration = Configuration(baseUrl, appToken)

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")
  val log = Logging(system, getClass)

  implicit val commandExecutionContext = system.dispatcher

  implicit val command = new StravaServiceCommand(configuration)

}
