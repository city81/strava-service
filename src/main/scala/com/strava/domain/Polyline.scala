package com.strava.domain


case class Polyline(id: String,
                    polyline: Option[String] = None,
                    summary_polyline: Option[String] = None,
                    resource_state: Int)