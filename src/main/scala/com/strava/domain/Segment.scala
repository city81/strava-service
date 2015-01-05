package com.strava.domain

import play.api.libs.json.{Reads, JsPath, Json}
import play.api.libs.functional.syntax._

// Split into two halves to overcome the problem serialising case classes with more than 22 members

case class SegmentOne(id: Long,
                      resource_state: Int,
                      name: String,
                      activity_type: String,
                      distance: Float,
                      average_grade: Float,
                      maximum_grade: Float,
                      elevation_high: Float,
                      elevation_low: Float,
                      start_latlng: Set[Float],
                      end_latlng: Set[Float],
                      climb_category: Int,
                      city: String,
                      state: String)

object SegmentOne {
  implicit val readsSegmentOne = Json.reads[SegmentOne]
}


case class SegmentTwo(PRIVATE: Option[Boolean] = None, // TODO
                      created_at: Option[String] = None,
                      updated_at: Option[String] = None,
                      total_elevation_gain: Option[Float] = None,
                      map: Option[Polyline] = None,
                      effort_count: Option[Int] = None,
                      athlete_count: Option[Int] = None,
                      hazardous: Option[Boolean] = None,
                      pr_time: Option[Int] = None,
                      pr_distance: Option[Float] = None,
                      starred: Boolean,
                      climb_category_desc: Option[String] = None)

object SegmentTwo {
  implicit val readsSegmentTwo = Json.reads[SegmentTwo]
}


case class Segment(partOne: SegmentOne,
                   partTwo: SegmentTwo)

object Segment {
  implicit val segmentReads: Reads[Segment] = (
    (JsPath).read[SegmentOne] and (JsPath).read[SegmentTwo])(Segment.apply _)
}