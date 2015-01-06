package com.strava.domain


case class Polyline(id: String,
                    polyline: String,
                    summary_polyline: Option[String] = None,
                    resource_state: Int)