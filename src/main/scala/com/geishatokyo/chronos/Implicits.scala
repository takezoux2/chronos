package com.geishatokyo.chronos

import TimeUnit._

object Implicits{

  implicit def intToWrapper(v : Int) = new TimeUnitWrapper(v)
  implicit def longToWrapper(v : Long) = new TimeUnitWrapper(v)
  
  
  def just( t : Justable) = Just(t)
  

}


class TimeUnitWrapper( value : Long){

  def millis = new Duration(value)
  def milli = new MilliSecond(value.toInt)
  def milliSeconds = new Duration(value)
  def milliSecond = new MilliSecond(value.toInt)
  def seconds = new Duration(value * TimeUnit.Second)
  def second = new Second(value.toInt)
  def minutes = new Duration(value * TimeUnit.Minute)
  def minute = new Minute(value.toInt)
  def hours = new Duration(value * TimeUnit.Hour)
  def hour = new Hour(value.toInt)
  def days = new Duration(value * TimeUnit.Day)
  def weeks = new Duration(value * TimeUnit.Day * 7)
  def weekOfYear = new WeekOfYear(value.toInt)
  def dayOfMonth = new DayOfMonth(value.toInt)
  def dayOfYear = new DayOfYear(value.toInt)
  
  def months = Month(value.toInt)
  def month = Month(value.toInt)
  def years = Year(value.toInt)
  def year = Year(value.toInt)
  
  def oclock = new Just(new Hour(value.toInt))
  
  def am = new Hour(value.toInt)
  def pm = new Hour(value.toInt + 12)
  
  
  
  // For Japanese
  def 月 = new Month(value.toInt)
  def 日 = new DayOfMonth(value.toInt)
  def 時 = new Hour(value.toInt)
  def 分 = new Minute(value.toInt)
  def 秒 = new Second(value.toInt)

}

class JustWrapper( value : Long) {
  def milli = new Just(new MilliSecond(value.toInt))
  def second = new Just(new Second(value.toInt))
  def minute = new Just(new Minute(value.toInt))
  def hour = new Just(new Hour(value.toInt))
  def dayOfMonth = new Just(new DayOfMonth(value.toInt))
  def dayOfYear = new Just(new DayOfYear(value.toInt))
  def oclock = new Just(new Hour(value.toInt))
  def am = new Just(new Hour(value.toInt))
  def pm = new Just(new Hour(value.toInt + 12))
  
  
}

