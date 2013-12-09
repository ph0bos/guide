package com.pressassociation.guide.service

import org.joda.time.DateTime
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import scala.concurrent._
import ExecutionContext.Implicits.global
import com.pressassociation.guide.model.{Episode, Programme, Movie, Person, Channel, Series}

/**
 * Created by stevenr on 02/12/2013.
 */
object GuideService {

  val mongoClient =  MongoClient(MongoClientURI("mongodb://<user>:<password>@<host>:<port>/guide"))("guide")

  /**
   * Return a list of available Categories
   *
   * @return a list of Categories
   */
  def getCategoryList : Future[List[String]] = future {
    val movies = (mongoClient("movie").distinct("category").toList)
    val series = (mongoClient("series").distinct("category").toList)
    val categories = ((movies ++ series).distinct)

    (categories).map(_.toString)
  }

  /**
   * Return a list of available Channels
   *
   * @return a list of Channels
   */
  def getChannelList : Future[List[Channel]] = future {
    val mongoColl = mongoClient("channel")
    val query = MongoDBObject.empty
    val filter = MongoDBObject("epg" -> null)
    val sort = MongoDBObject("name" -> 1)

    (mongoColl.find(query, filter).sort(sort).toList).map(grater[Channel].asObject(_))
  }

  /**
   * Return a Channel
   *
   * @param id the channel identifier
   * @return a Channel
   */
  def getChannel(id: String) : Future[Option[Channel]] = future {
    val mongoColl = mongoClient("channel")
    val query = MongoDBObject("id" -> id)

    (mongoColl.findOne(query)).map(grater[Channel].asObject(_))
  }

  /**
   * Return a list of available Movies
   *
   * @return a list of Movies
   */
  def getMovieList(search: String) : Future[List[Movie]] = future {
    val mongoColl = mongoClient("movie")
    val title = "(?i)" + search
    val query = MongoDBObject("title" -> title.r)
    val filter = MongoDBObject("id" -> 1, "title" -> 1, "year" -> 1)

    (mongoColl.find(query, filter).toList).map(grater[Movie].asObject(_))
  }

  /**
   * Return a Movie
   *
   * @param id the movie identifier
   * @return a Movie
   */
  def getMovie(id: String) : Future[Option[Movie]] = future {
    val mongoColl = mongoClient("movie")
    val query = MongoDBObject("id" -> id)

    (mongoColl.findOne(query)).map(grater[Movie].asObject(_))
  }

  /**
   * Return the cast of a Movie
   *
   * @param id the movie identifier
   * @return a cast list of a Movie
   */
  def getMovieCast(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("movie")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("cast", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  /**
   * Return the crew of a Movie
   *
   * @param id the movie identifier
   * @return a crew list of a Movie
   */
  def getMovieCrew(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("movie")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("crew", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  /**
   * Return a list of available Series
   *
   * @return a list of Series
   */
  def getSeriesList(search: String) : Future[List[Series]] = future {
    val mongoColl = mongoClient("series")
    val title = "(?i)" + search + ""
    val query = MongoDBObject("title" -> title.r)
    val filter = MongoDBObject("id" -> 1, "title" -> 1, "category" -> 1)
    val sort = MongoDBObject("title" -> 1)

    (mongoColl.find(query, filter).sort(sort).toList).map(grater[Series].asObject(_))
  }

  /**
   * Return a Series
   *
   * @param id the series identifier
   * @return a Series
   */
  def getSeries(id: String) : Future[Option[Series]] = future {
    val mongoColl = mongoClient("series")
    val query = MongoDBObject("id" -> id)

    (mongoColl.findOne(query)).map(grater[Series].asObject(_))
  }

  /**
   * Return a Series cast list
   *
   * @param id the series identifier
   * @return a Series cast list
   */
  def getSeriesCast(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("series")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("cast", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  /**
   * Return a Series crew list
   *
   * @param id the series identifier
   * @return a Series crew list
   */
  def getSeriesCrew(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("series")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("cast", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
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
    val mongoColl = mongoClient("person")
    val name = "(?i)" + search
    val query = MongoDBObject("name" -> name.r)

    (mongoColl.find(query).toList).map(grater[Person].asObject(_))
  }

  /**
   * Return a Person
   *
   * @param id the person identifier
   * @return a Person
   */
  def getPerson(id: String) : Future[Option[Person]] = future {
    val mongoColl = mongoClient("person")
    val query = MongoDBObject("id" -> id)

    (mongoColl.findOne(query)).map(grater[Person].asObject(_))
  }

  /**
   * Return an Episode
   *
   * @param id the episode identifier
   * @return a Episode
   */
  def getEpisode(id: String) : Future[Option[Episode]] = future {
    val mongoColl = mongoClient("episode")
    val query = MongoDBObject("id" -> id)

    (mongoColl.findOne(query)).map(grater[Episode].asObject(_))
  }

  /**
   * Return an Episode Cast list
   *
   * @param id the episode identifier
   * @return an Episode Cast list
   */
  def getEpisodeCast(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("episode")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("cast", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  /**
   * Return an Episode Crew list
   *
   * @param id the episode identifier
   * @return an Episode Crew list
   */
  def getEpisodeCrew(id: String) : Future[List[Person]] = future {
    val mongoColl = mongoClient("episode")
    val query = MongoDBObject("id" -> id)

    (mongoColl.distinct("crew", query).toList).map(
      res => grater[Person].fromJSON(res.toString)
    )
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String) : Future[List[Programme]] = future {
    val mongoColl = mongoClient("programme")
    val query = MongoDBObject("channel.id" -> channelId)
    val sort = MongoDBObject("dateTime" -> 1)
    val filter = MongoDBObject("programme" -> 1)

    (mongoColl.find(query, filter).sort(sort).toList).map(grater[Programme].asObject(_))
  }

  /**
   * Return an list of Programmes
   *
   * @param channelId the channel identifier
   * @param start the start date
   * @param end the end date
   * @return a programme list
   */
  def getScheduledProgrammeList(channelId: String, start: DateTime, end: DateTime) : Future[List[Programme]] = future {
    val mongoColl = mongoClient("programme")
    val query = MongoDBObject("channel.id" -> channelId)

    val dateRange = "dateTime" $gte start.toString() $lte end.toString()

    val sort = MongoDBObject("dateTime" -> 1)
    val filter = MongoDBObject("cast" -> 0, "crew" -> 0)

    (mongoColl.find(query ++ dateRange, filter).sort(sort).toList).map(
      res => grater[Programme].asObject(res)
    )
  }
}
