package com.pressassociation.guide.service

import com.pressassociation.guide.model._
import dispatch.{:/, Req}
import scala.concurrent.{ExecutionContext, Future}
import com.pressassociation.guide.akka.DispatchAkka
import org.json4s._
import org.json4s.jackson.JsonMethods._
import java.util.Date
import org.joda.time.DateTime

/**
 * Created by stevenr on 02/12/2013.
 */
object GuideService {

  protected implicit val jsonFormats: Formats = DefaultFormats
  protected implicit val baseUrl: Req = :/("guide.28.io")

  /**
   * Return a list of available Categories
   *
   * @return a list of Categories
   */
  def getCategoryList()(implicit ctx: ExecutionContext) : Future[Seq[String]] = {
    val req = baseUrl / "category" / "list.jq"

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[String]]
    }
  }

  /**
   * Return a list of available Channels
   *
   * @return a list of Channels
   */
  def getChannelList()(implicit ctx: ExecutionContext) : Future[Seq[Channel]] = {
    val req = baseUrl / "channel" / "list.jq"

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Channel]]
    }
  }

  /**
   * Return a Channel
   *
   * @param id the channel identifier
   * @return a Channel
   */
  def getChannel(id: String)(implicit ctx: ExecutionContext): Future[Channel] = {
    var req = baseUrl / "channel" / "detail.jq"
    req = req <<? Map("channelId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Channel]
    }
  }

  /**
   * Return a list of available Movies
   *
   * @return a list of Movies
   */
  def getMovieList(search: String)(implicit ctx: ExecutionContext) : Future[Seq[Movie]] = {
    var req = baseUrl / "movie" / "list.jq"
    req = req <<? Map("search" -> search)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Movie]]
    }
  }

  /**
   * Return a Movie
   *
   * @param id the movie identifier
   * @return a Movie
   */
  def getMovie(id: String)(implicit ctx: ExecutionContext) : Future[Movie] = {
    var req = baseUrl / "movie" / "detail.jq"
    req = req <<? Map("movieId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Movie]
    }
  }

  /**
   * Return the cast of a Movie
   *
   * @param id the movie identifier
   * @return a cast list of a Movie
   */
  def getMovieCast(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "person" / "detail-cast.jq"
    req = req <<? Map("id" -> id, "collection" -> "movie")

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return the crew of a Movie
   *
   * @param id the movie identifier
   * @return a crew list of a Movie
   */
  def getMovieCrew(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "person" / "detail-crew.jq"
    req = req <<? Map("id" -> id, "collection" -> "movie")

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return a list of available Series
   *
   * @return a list of Series
   */
  def getSeriesList(search: String)(implicit ctx: ExecutionContext) : Future[Seq[Series]] = {
    var req = baseUrl / "series" / "list.jq"
    req = req <<? Map("search" -> search)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Series]]
    }
  }

  /**
   * Return a Series
   *
   * @param id the series identifier
   * @return a Series
   */
  def getSeries(id: String)(implicit ctx: ExecutionContext) : Future[Series] = {
    var req = baseUrl / "series" / "detail.jq"
    req = req <<? Map("seriesId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Series]
    }
  }

  /**
   * Return a Series cast list
   *
   * @param id the series identifier
   * @return a Series cast list
   */
  def getSeriesCast(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "series" / "detail-cast.jq"
    req = req <<? Map("seriesId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return a Series crew list
   *
   * @param id the series identifier
   * @return a Series crew list
   */
  def getSeriesCrew(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "series" / "detail-crew.jq"
    req = req <<? Map("seriesId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return a Series episode list
   *
   * @param id the series identifier
   * @return a Series episode list
   */
  def getSeriesEpisodeList(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Episode]] = {
    var req = baseUrl / "series" / "list-episode.jq"
    req = req <<? Map("seriesId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Episode]]
    }
  }

  /**
   * Return a Series season list
   *
   * @param id the series identifier
   * @return a Series season list
   */
  def getSeriesSeasonList(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Season]] = {
    var req = baseUrl / "series" / "list-season.jq"
    req = req <<? Map("seriesId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Season]]
    }
  }

  /**
   * Return a list of available Persons
   *
   * @return a list of Persons
   */
  def getPersonList(search: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "person" / "list.jq"
    req = req <<? Map("search" -> search)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return a Person
   *
   * @param id the person identifier
   * @return a Person
   */
  def getPerson(id: String)(implicit ctx: ExecutionContext) : Future[Person] = {
    var req = baseUrl / "person" / "detail.jq"
    req = req <<? Map("personId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Person]
    }
  }

  /**
   * Return an Episode
   *
   * @param id the episode identifier
   * @return a Episode
   */
  def getEpisode(id: String)(implicit ctx: ExecutionContext) : Future[Episode] = {
    var req = baseUrl / "episode" / "detail.jq"
    req = req <<? Map("episodeId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Episode]
    }
  }

  /**
   * Return an Episode Cast list
   *
   * @param id the episode identifier
   * @return an Episode Cast list
   */
  def getEpisodeCast(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "episode" / "detail-cast.jq"
    req = req <<? Map("episodeId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return an Episode Crew list
   *
   * @param id the episode identifier
   * @return an Episode Crew list
   */
  def getEpisodeCrew(id: String)(implicit ctx: ExecutionContext) : Future[Seq[Person]] = {
    var req = baseUrl / "episode" / "detail-crew.jq"
    req = req <<? Map("episodeId" -> id)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Person]]
    }
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String)(implicit ctx: ExecutionContext) : Future[Seq[Programme]] = {
    var req = baseUrl / "schedule" / "list.jq"
    req = req <<? Map("channelId" -> channelId)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Programme]]
    }
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @param start the start date
   * @param end the end date
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String, start: DateTime, end: DateTime)(implicit ctx: ExecutionContext) : Future[Seq[Programme]] = {
    var req = baseUrl / "schedule" / "list.jq"
    req = req <<? Map("start" -> start.toString)
    req = req <<? Map("end" -> end.toString)
    req = req <<? Map("channelId" -> channelId)

    (DispatchAkka.getData(req)).map {
      case res => parse(res).extract[Seq[Programme]]
    }
  }
}
