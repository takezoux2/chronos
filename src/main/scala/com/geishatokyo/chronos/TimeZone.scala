package com.geishatokyo.chronos

class TimeZone(val id : String,offsetMillis : Long){

  def convert( unixTimeMillis : Long) = unixTimeMillis + offsetMillis

}

object TimeZone{

  def default = {
    val tz = java.util.TimeZone.getDefault()
    fromJavaTimeZone(tz)
  }
  
  implicit def fromJavaTimeZone(timeZone : java.util.TimeZone) = {
    new TimeZone(timeZone.getID,timeZone.getRawOffset)
  }
}