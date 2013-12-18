package com.pressassociation.guide.service

import org.joda.time.DateTime
import com.mongodb.casbah.Imports._
import scala.concurrent._
import ExecutionContext.Implicits.global
import com.pressassociation.guide.model._
import com.pressassociation.guide.model.{Channel}


/**
 * Created by stevenr on 02/12/2013.
 */
object GuideService {

  /**
   * Return a list of available Categories
   *
   * @return a list of Categories
   */
  def getCategoryList : Future[List[String]] = future {
    ((MovieDAO.getDistinctCategories()
      ++ SeriesDAO.getDistinctCategories()).distinct)
      .map(_.toString)
  }

  /**
   * Return a list of available Platforms
   *
   * @return a list of Platforms
   */
  def getPlatformList : Future[List[Platform]] = future {
    PlatformDAO
      .find(ref = MongoDBObject.empty, keys = MongoDBObject("id" -> 1, "name" -> 1))
      .sort(orderBy = MongoDBObject("name" -> 1))
      .toList
  }

  /**
   * Return a Platform
   *z
   * @param id the platform identifier
   * @return a Platform
   */
  def getPlatform(id: String) : Future[Option[Platform]] = future {
    PlatformDAO
      .findOneById(id = id)
  }

  /**
   * Return a list of available Channels
   *
   * @return a list of Channels
   */
  def getChannelList : Future[List[Channel]] = future {
    ChannelDAO
      .find(ref = MongoDBObject.empty)
      .sort(MongoDBObject("mediaType" -> -1, "number" -> 1))
      .toList
  }

  /**
   * Return a list of available Channels
   *f
   * @return a list of Channels
   */
  def getChannelList(platformId: String, regionId: String) : Future[List[Epg]] = future {
    EpgDAO
      .find(ref = MongoDBObject("platform.id" -> platformId) ++ $or(("regional" -> false), ("region.id" -> regionId)))
      .sort(MongoDBObject("mediaType" -> -1, "number" -> 1))
      .toList
  }

  /**
   * Return a Channel
   *
   * @param id the channel identifier
   * @return a Channel
   */
  def getChannel(id: String) : Future[Option[Channel]] = future {
    ChannelDAO
      .findOneById(id = id)
  }

  /**
   * Return a list of available Movies
   *
   * @return a list of Movies
   */
  def getMovieList(search: String) : Future[List[Movie]] = future {
    MovieDAO
      .find(ref = MongoDBObject("title" -> ("(?i)" + search).r), keys = MongoDBObject("id" -> 1, "title" -> 1, "year" -> 1))
      .toList
  }

  /**
   * Return a Movie
   *
   * @param id the movie identifier
   * @return a Movie
   */
  def getMovie(id: String) : Future[Option[Movie]] = future {
    MovieDAO
      .findOneById(id = id)
  }

  /**
   * Return the cast of a Movie
   *
   * @param id the movie identifier
   * @return a cast list of a Movie
   */
  def getMovieCast(id: String) : Future[List[Person]] = future {
    MovieDAO
      .getCast(id)
  }

  /**
   * Return the crew of a Movie
   *
   * @param id the movie identifier
   * @return a crew list of a Movie
   */
  def getMovieCrew(id: String) : Future[List[Person]] = future {
    MovieDAO
      .getCrew(id)
  }

  /**
   * Return a list of available Series
   *
   * @return a list of Series
   */
  def getSeriesList(search: String) : Future[List[Series]] = future {
    SeriesDAO
      .find(ref = MongoDBObject("title" -> ("(?i)" + search).r), keys = MongoDBObject("id" -> 1, "title" -> 1, "category" -> 1))
      .sort(orderBy = MongoDBObject("title" -> 1))
      .toList
  }

  /**
   * Return a Series
   *
   * @param id the series identifier
   * @return a Series
   */
  def getSeries(id: String) : Future[Option[Series]] = future {
    SeriesDAO
      .findOneById(id = id)
  }

  /**
   * Return a Series cast list
   *
   * @param id the series identifier
   * @return a Series cast list
   */
  def getSeriesCast(id: String) : Future[List[Person]] = future {
    EpisodeDAO
      .getSeriesCast(id)
  }

  /**
   * Return a Series crew list
   *
   * @param id the series identifier
   * @return a Series crew list
   */
  def getSeriesCrew(id: String) : Future[List[Person]] = future {
    EpisodeDAO
      .getSeriesCrew(id)
  }

  /**
   * Return a Series episode list
   *
   * @param id the series identifier
   * @return a Series episode list
   */
  def getSeriesEpisodeList(id: String) = future {

  }

  /**
   * Return a Series season list
   *
   * @param id the series identifier
   * @return a Series season list
   */
  def getSeriesSeasonList(id: String) = future {

  }

  /**
   * Return a list of available Persons
   *
   * @return a list of Persons
   */
  def getPersonList(search: String) : Future[List[Person]] = future {
    PersonDAO
      .find(ref = MongoDBObject("name" -> ("(?i)" + search).r), keys = MongoDBObject("id" -> 1, "name" -> 1, "image" -> -1))
      .sort(orderBy = MongoDBObject("name" -> 1))
      .toList
  }

  /**
   * Return a Person
   *
   * @param id the person identifier
   * @return a Person
   */
  def getPerson(id: String) : Future[Option[Person]] = future {
    PersonDAO
      .findOneById(id = id)
  }

  /**
   * Return an Episode
   *
   * @param id the episode identifier
   * @return a Episode
   */
  def getEpisode(id: String) : Future[Option[Episode]] = future {
    EpisodeDAO
      .findOneById(id = id)
  }

  /**
   * Return an Episode Cast list
   *
   * @param id the episode identifier
   * @return an Episode Cast list
   */
  def getEpisodeCast(id: String) : Future[List[Person]] = future {
    EpisodeDAO
      .getCast(id)
  }

  /**
   * Return an Episode Crew list
   *
   * @param id the episode identifier
   * @return an Episode Crew list
   */
  def getEpisodeCrew(id: String) : Future[List[Person]] = future {
    EpisodeDAO
      .getCrew(id)
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String) : Future[List[Programme]] = future {
    ProgrammeDAO
      .find(ref = MongoDBObject("channel.id" -> channelId), keys = MongoDBObject("programme" -> 1))
      .sort(orderBy = MongoDBObject("dateTime" -> 1))
      .toList
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @param start the start date
   * @param end the end date
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String, movieId: String, seriesId: String, personId: String, start: DateTime, end: DateTime) : Future[List[Programme]] = future {
    val builder = MongoDBObject.newBuilder
    if (channelId != null) builder += "channel.id" -> channelId
    if (movieId != null) builder += "movie.id" -> movieId
    if (seriesId != null) builder += "series.id" -> seriesId
    if (personId != null) builder += "cast.id" -> personId

    ProgrammeDAO
      .find(ref = builder.result ++ ("dateTime" $gte start.toString() $lte end.toString()), keys = MongoDBObject("cast" -> 0, "crew" -> 0))
      .sort(orderBy = MongoDBObject("dateTime" -> 1))
      .toList
  }
}
