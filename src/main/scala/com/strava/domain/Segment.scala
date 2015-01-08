package com.strava.domain

// TODO need a better name!
case class SegmentsForGivenArea(segments: Seq[BoundSegment])

// TODO need a better name!
case class BoundSegment(id: Long,
                        resource_state: Int,
                        name: String,
                        climb_category: Int,
                        climb_category_desc: Option[String] = None,
                        avg_grade: Float,
                        start_latlng: Set[Float],
                        end_latlng: Set[Float],
                        elev_difference: Float,
                        distance: Float,
                        points: String)

case class Segment(id: Long,
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
                   state: String,
                   `private`: Option[Boolean] = None,
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