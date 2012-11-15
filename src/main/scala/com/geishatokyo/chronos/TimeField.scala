package com.geishatokyo.chronos

import java.util.Calendar._
import Implicits._

trait TimeField{
}


trait Justable extends TimeField{
  
  def just = Just(this)
  
  def loaf : TDuration
  
}


trait SingleTimeField extends Justable{

  def fieldId : Int
  def value : Int
  
}


case class MilliSecond(value : Int) extends SingleTimeField{
  val fieldId = MILLISECOND
  
  val loaf = 1.seconds
}
case class Second(value : Int) extends SingleTimeField{
  val fieldId = SECOND
  val loaf = 1.minutes
}
case class Minute(value : Int) extends SingleTimeField{
  val fieldId = MINUTE
  val loaf = 1.hours
}
case class Hour(value : Int) extends SingleTimeField{
  val fieldId = HOUR
  val loaf = 1.days
}

case class WeekOfYear(value : Int) extends SingleTimeField{
  val fieldId = WEEK_OF_YEAR
  val loaf = 1.years

}

case class DayOfMonth(value : Int) extends SingleTimeField{
  val fieldId = DAY_OF_MONTH
  val loaf = 1.months
}
case class DayOfYear(value : Int) extends SingleTimeField{
  val fieldId = DAY_OF_YEAR
  val loaf = 1.years
}
case class MonthAndDay(m : Int, d : Int) extends Justable{
  def in(year : Int) = YearMonthAndDay(year,m,d)
  val loaf = 1.years
}
case class YearMonthAndDay(y : Int, m : Int, d : Int) extends Justable{
  val loaf = 1.years
}

object YearMonthAndDay{

  implicit def toDateTime( ymd : YearMonthAndDay) = {
    DateTime.now.set( just(ymd))
  }
}

case class Just(tf : Justable) extends TimeField{
}


