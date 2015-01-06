package com.strava.domain

case class Club(id: Long,
                resource_state: Int,
                name: String,
                profile_medium: String,
                profile: String,
                description: String,
                club_type: String,
                sport_type: String,
                city: String,
                state: String,
                country: String,
                `private`: Boolean,
                member_count: Int)