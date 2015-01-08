package com.strava.domain

case class SegmentLeaderboard(entry_count: Int,
                              entries: Seq[LeaderboardEntry])

case class LeaderboardEntry(athlete_name: String,
                            athlete_id: Int,
                            athlete_gender: String,
                            average_hr: Option[Float] = None,
                            average_watts: Option[Float] = None,
                            distance: Float,
                            elapsed_time: Int,
                            moving_time: Int,
                            start_date: String,
                            start_date_local: String,
                            activity_id: Int,
                            effort_id: Int,
                            rank: Int,
                            athlete_profile: String)