package com.strava

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import akka.io.IO
import com.strava.service.StravaServiceCommand
import com.typesafe.config.ConfigFactory
import spray.can.Http

object Boot extends App {

  val conf = ConfigFactory.load()

  val serverPort = conf.getInt("port")

  val appToken = conf.getString("stravaService.appToken")

  val configuration = Configuration(appToken)

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")
  val log = Logging(system, getClass)

  implicit val commandExecutionContext = system.dispatcher

  implicit val command = new StravaServiceCommand(configuration)

}
