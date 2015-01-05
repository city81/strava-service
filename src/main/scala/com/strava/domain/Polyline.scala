package com.strava.domain

import play.api.libs.json.Json

case class Polyline(id: String,
                    polyline: String,
                    summary_polyline: Option[String] = None,
                    resource_state: Int)

object Polyline {
  implicit val readsPolyline = Json.reads[Polyline]
}