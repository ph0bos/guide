package com.pressassociation.guide.util

import com.github.nscala_time.time.Imports._

object DateTimeUtil {

  val DatePattern = """\d{4}\-\d{2}\-\d{2}""".r
  val DateTimePattern = """\d{4}\-\d{2}\-\d{2}T\d{2}:\d{2}:\d{2}""".r
  val NowPlus = """now\.plus\.(\d+)""".r
  val NowMinus = """now\.minus\.(\d+)""".r

  /**
   * Parse an interpret an input string to return a valid DateTime object
   *
   * @param input
   * @return a datetime object
   */
  def parseString(input: String) : DateTime = {
    input match {
      case DatePattern() => new DateTime(input)
      case DateTimePattern() => new DateTime(input)
      case NowPlus(hourPlus) => (DateTime.now).plusHours(Integer.parseInt(hourPlus))
      case NowMinus(hourMinus) => (DateTime.now).minusHours(Integer.parseInt(hourMinus))
      case "now" => DateTime.now
      case "tomorrow" => DateTime.tomorrow
      case _ => throw new Exception("Unhandled string format: " + input)
    }
  }
}
