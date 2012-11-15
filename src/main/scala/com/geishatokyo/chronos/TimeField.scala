package com.geishatokyo.chronos

import java.util.Calendar._

trait TimeField{
}

trait SingleTimeField extends TimeField{

  def fieldId : Int
  def value : Int
  
  def just = Just(this)
}


case class MilliSecond(value : Int) extends SingleTimeField{
  val fieldId = MILLISECOND
}
case class Second(value : Int) extends SingleTimeField{
  val fieldId = SECOND
}
case class Minute(value : Int) extends SingleTimeField{
  val fieldId = MINUTE
}
case class Hour(value : Int) extends SingleTimeField{
  val fieldId = HOUR
}
case class DayOfMonth(value : Int) extends SingleTimeField{
  val fieldId = DAY_OF_MONTH
}
case class DayOfYear(value : Int) extends SingleTimeField{
  val fieldId = DAY_OF_YEAR
}
case class MonthAndDay(m : Int, d : Int) extends TimeField{
}
case class Just(tf : SingleTimeField) extends TimeField{
}


