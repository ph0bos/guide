package com.pressassociation.guide.util

import org.scalatest._

class DateTimeUtilTest extends FlatSpec with Matchers {

  "DateTimeUtil" should "return not null when called with 'now'" in {
    val date = DateTimeUtil.parseString("now")
    date should not be null
  }

  "DateTimeUtil" should "return not null when called with 'now.plus.2'" in {
    val date = DateTimeUtil.parseString("now.plus.2")
    date should not be null
  }

  "DateTimeUtil" should "return not null when called with 'now.minus.2'" in {
    val date = DateTimeUtil.parseString("now.minus.2")
    date should not be null
  }

  "DateTimeUtil" should "return not null when called with a date string" in {
    val date = DateTimeUtil.parseString("1970-01-01")
    date should not be null
  }

  "DateTimeUtil" should "return not null when called with a datetime string" in {
    val date = DateTimeUtil.parseString("1970-01-01T12:00:00")
    date should not be null
  }
}
