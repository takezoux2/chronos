package com.geishatokyo.chronos


trait TDuration{
}



case class Duration(val milliSeconds : Long) extends TDuration{
  import TimeUnit._
  
  def milliSecond = milliSeconds
  def millis = milliSeconds
  def milli = milliSeconds
  
  def seconds = milliSeconds / Second
  def second = seconds
  
  def minutes = milliSeconds / Minute
  def minute = minutes
  
  def hours = milliSeconds / Hour
  def hour = hours
  
  def days = milliSeconds / Day
  def day = days
  
  def years = milliSeconds / Year
  def year = years
  
  def +(duration : Duration) =  new Duration(milliSeconds + duration.milliSeconds)
  
  def -(d : Duration) = new Duration(milliSeconds - d.milliSeconds)
  
  def *( v : Int) = new Duration(milliSeconds * v)
  def /(v : Int) = new Duration(milliSeconds / v)
  
  
  def unary_- = new Duration(-milliSeconds)
}