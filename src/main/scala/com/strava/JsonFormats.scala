package com.strava

import net.liftweb.json.DefaultFormats
import spray.httpx.LiftJsonSupport

object JsonFormats extends LiftJsonSupport {
  implicit val liftJsonFormats = DefaultFormats
}