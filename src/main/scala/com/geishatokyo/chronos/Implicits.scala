package com.geishatokyo.chronos

import TimeUnit._

object Implicits{

  implicit def intToWrapper(v : Int) = new TimeUnitWrapper(v)
  implicit def longToWrapper(v : Long) = new TimeUnitWrapper(v)

}


class TimeUnitWrapper( value : Long){

  def millis = new Duration(value)
  def milli = new MilliSecond(value.toInt)
  def seconds = new Duration(value * TimeUnit.Second)
  def second = new Second(value.toInt)
  def minutes = new Duration(value * TimeUnit.Minute)
  def minute = new Minute(value.toInt)
  def hours = new Duration(value * TimeUnit.Hour)
  def hour = new Hour(value.toInt)
  def days = new Duration(value * TimeUnit.Day)
  def dayOfMonth = new DayOfMonth(value.toInt)
  def dayOfYear = new DayOfYear(value.toInt)
  
  def months = Month(value.toInt)
  def years = Year(value.toInt)

}