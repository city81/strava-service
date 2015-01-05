package com.strava.domain

import play.api.libs.json.Json

case class RequestBody(id: String)

object RequestBody {
  implicit val writesRequestBody = Json.writes[RequestBody]
}