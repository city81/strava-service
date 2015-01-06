package com.strava.domain

case class Gear(id: String,
                primary: Boolean,
                name: String,
                distance: Int,
                brand_name: Option[String] = None,
                model_name: Option[String] = None,
                frame_type: Option[String] = None,
                description: Option[String] = None,
                resource_state: Int)