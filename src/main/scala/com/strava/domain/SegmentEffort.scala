package com.strava.domain

case class SegmentEffort(id: Long,
                         resource_state: Int,
                         name: String,
                         segment: Segment,
                         activity: Option[LinkId] = None,
                         athlete: Option[LinkId] = None,
                         kom_rank: Option[Int] = None,
                         pr_rank: Option[Int] = None,
                         elapsed_time: Int,
                         moving_time: Int,
                         start_date: String,
                         start_date_local: String,
                         distance: Float,
                         start_index: Int,
                         end_index: Int)