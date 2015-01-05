package com.strava.domain

import play.api.libs.json.Json

// TODO
case class Athlete(id: Long)

object Athlete {
  implicit val readsAthlete = Json.reads[Athlete]
}