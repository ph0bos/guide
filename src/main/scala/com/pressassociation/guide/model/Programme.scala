package com.pressassociation.guide.model

case class Programme (
  id: String,
  dateTime: Option[java.util.Date],
  duration: Int,
  title: String,
  genre: String,
  synopsis1: Option[String],
  synopsis2: Option[String],
  synopsis3: Option[String],
  stereo: Boolean,
  subtitles: Boolean,
  surround: Boolean,
  widescreen: Boolean,
  hd: Boolean,
  `3d`: Boolean,
  repeat: Boolean,
  premiere: Boolean,
  channel: Channel,
  movie: Option[Movie],
  series: Option[Series],
  season: Option[Season],
  episode: Option[Episode]
)
