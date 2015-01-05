package com.strava.domain

import play.api.libs.json.Json

// TODO
case class Activity(id: Long)

object Activity {
  implicit val readsActivity = Json.reads[Activity]
}