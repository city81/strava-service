package com.strava.domain

import play.api.libs.json.Json

case class SegmentEffort(id: Long,
                         resource_state: Int,
                         name: String,
                         segment: Segment,
                         activity: Option[Activity] = None, // TODO
                         athlete: Option[Athlete] = None, // TODO
                         kom_rank: Option[Int] = None,
                         pr_rank: Option[Int] = None,
                         elapsed_time: Int,
                         moving_time: Int,
                         start_date: String,
                         start_date_local: String,
                         distance: Float,
                         start_index: Int,
                         end_index: Int)

object SegmentEffort {
  implicit val readsSegmentEffort = Json.reads[SegmentEffort]
}