package com.geishatokyo.chronos

import java.util.{Date,Calendar}



object DateTime{
  
  def now = {
    new DateTime(Calendar.getInstance)
  }
  
  def apply(unixTime : Long) = {
    val c = Calendar.getInstance
    c.setTimeInMillis(unixTime)
    new DateTime(c)
  }
  
  implicit def fromDate(date : Date) = {
    val c = Calendar.getInstance
    c.setTime(date)
    new DateTime(c)
  }
  implicit def fromCalendar(calendar : Calendar) = {
    new DateTime(copy(calendar))
  }
  
  def copy(calendar : Calendar) = calendar.clone.asInstanceOf[Calendar]

  val DaysOfMonths = Seq(31,28,31,30,31,30,31,31,30,31,30,31)
  private val AccumDaysOfMonths = Seq(0,31,59,90,120,151,181,212,243,273,304,334,365)
  val DaysOfMonthsInLeapYear = Seq(31,29,31,30,31,30,31,31,30,31,30,31)
  private val AccumDaysOfMonthsInLeapYear = Seq(0,31,60,91,121,152,182,213,244,274,305,335,366)
  
  
  
  def isLeapYear(year : Int ) = year % 4 == 0 &&
    (year % 400 == 0 || year % 100 != 0)
  
  val DefaultFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS")
  
}

case class DateTime private(calendar : Calendar) extends Ordered[DateTime]{
  
  
  import DateTime._
  
  def toLong() = getTime()
  def getTime() = calendar.getTimeInMillis() 
  
  def milli = milliSecs
  def millis = milliSecs
  def milliSec = milliSecs
  def milliSecs = calendar.get(Calendar.MILLISECOND)
  
  def second = seconds
  def seconds = calendar.get(Calendar.SECOND)
  
  def minute = minutes
  def minutes = calendar.get(Calendar.MINUTE)
  
  def hour = hours
  def hours = calendar.get(Calendar.HOUR)
  
  def isAm = am_?
  def am_? = hours < 12
  def isPm = pm_?
  def pm_? = hours >= 12
  
  def year = calendar.get(Calendar.YEAR)
  
  lazy val dayOfYear = {
    calendar.get(Calendar.DAY_OF_YEAR)
  }
  
  def isLeapYear = leapYear_?
  def leapYear_? = DateTime.isLeapYear(year)
  
  def nearestLeapYear = {
    if(leapYear_?) year
    else ( (year + 1) to (year + 8)).find(y => DateTime.isLeapYear(y)).get
  }
  
  def month = {
    calendar.get(Calendar.MONTH) + 1
  }
  
  def dayOfMonth = {
    calendar.get(Calendar.DAY_OF_MONTH)
  }
  
  def dayOfWeek = {
    DayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))
  }
  
  def daysOfThisMonth = {
    if(leapYear_?) {
      DaysOfMonths(month - 1)
    }else{
      DaysOfMonthsInLeapYear(month - 1)
    }
  }
  
  def +(duration : TDuration) : DateTime = {
    duration match{
      case Duration(mSec) => {
        val cal = DateTime.copy(calendar)
        cal.setTimeInMillis(getTime + mSec)
        new DateTime(cal)
      }
      case Month(m) => {
        val cal = DateTime.copy(calendar)
        cal.add(Calendar.MONTH,m)
        new DateTime(cal)
      
      }
      case Year(y) => {
        val cal = DateTime.copy(calendar)
        cal.add(Calendar.YEAR,y)
        new DateTime(cal)
      }
    }
  }
  
  def -(dateTime : DateTime) : Duration = {
    new Duration( this.getTime - dateTime.getTime)
  }
  
  def -(duration : TDuration) : DateTime = {
    duration match{
      case Duration(mSec) => {
        val cal = DateTime.copy(calendar)
        cal.setTimeInMillis(getTime - mSec)
        new DateTime(cal)
      }
      case Month(m) => {
        val cal = DateTime.copy(calendar)
        cal.add(Calendar.MONTH,-m)
        new DateTime(cal)
      
      }
      case Year(y) => {
        val cal = DateTime.copy(calendar)
        cal.add(Calendar.YEAR,-y)
        new DateTime(cal)
      }
    }
      
  
  }
  
  def set( fields : TimeField*) = {
    val cal = DateTime.copy(calendar)
    
    fields.foreach({
      case MonthAndDay(m,d) => {
        cal.set(Calendar.MONTH,m - 1)
        cal.set(Calendar.DAY_OF_MONTH,d)
      }
      case Month(m) => {
        cal.set(Calendar.MONTH,m - 1)
      }
      case Just(field) => field match{
        case MilliSecond(v) => {
          cal.set(Calendar.MILLISECOND,v)
        }
        case Second(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,v)
        }
        case Minute(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,v)
        }
        case Hour(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,v)
        }
        case DayOfMonth(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,v)
        }
        case DayOfYear(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,v)
        }
        case Month(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,1)
          cal.set(Calendar.MONTH,v - 1)
        }
        case Year(v) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,1)
          cal.set(Calendar.MONTH,0)
          cal.set(Calendar.YEAR,v)
        }
        case MonthAndDay(m,d) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,d)
          cal.set(Calendar.MONTH,m - 1)
        }
        case YearMonthAndDay(y,m,d) => {
          cal.set(Calendar.MILLISECOND,0)
          cal.set(Calendar.SECOND,0)
          cal.set(Calendar.MINUTE,0)
          cal.set(Calendar.HOUR,0)
          cal.set(Calendar.DAY_OF_MONTH,d)
          cal.set(Calendar.MONTH,m - 1)
          cal.set(Calendar.YEAR,y)
        }
        
      }
      case f : SingleTimeField => {
        cal.set(f.fieldId,f.value)
      }
    })
  
    new DateTime(cal)
  }
  
  def next(just : Just) : DateTime = next(just.tf)
  
  def next( justable : Justable) : DateTime = {
    val j = this.set(justable)
    if( j < this){
      j + justable.loaf
    }else{
      j
    }
  }
  def before(just : Just) : DateTime = before(just.tf)
  def before( justable : Justable) : DateTime = {
    val j = this.set(justable)
    if( j > this){
      j - justable.loaf
    }else{
      j
    }
  }
  
  
  def next( dayOfWeek : DayOfWeek.Value): DateTime = {
    if(this.dayOfWeek == dayOfWeek) this
    else{
      val diff = {
        val d = dayOfWeek.id - this.dayOfWeek.id
        if(d < 0) d + 7
        else d
      }
      
      import Implicits._
      this + diff.days
    }
  }
  
  def before( dayOfWeek : DayOfWeek.Value) : DateTime= {
    if(this.dayOfWeek == dayOfWeek) this
    else{
      val diff = {
        val d = this.dayOfWeek.id - dayOfWeek.id
        if(d < 0) d + 7
        else d
      }
      
      import Implicits._
      this - diff.days
    }
  }
  
  
  
  def to(end : DateTime) = new TimeRange(this,end)
  
  def until(end : DateTime) = {
    import Implicits._
    
    new TimeRange(this, end - 1.millis)
  }
  
  
  def copy() = {
    new DateTime(DateTime.copy(calendar))
  }
  
  def toDate() = calendar.getTime()
  
  def compare( dt : DateTime) = {
    calendar.compareTo(dt.calendar)
  }
  
  override def toString() = {
    DefaultFormat.synchronized{
      DefaultFormat.format(calendar.getTime)
    }
  }
  
  def format( dateFormat : java.text.DateFormat) : String = {
    dateFormat.synchronized{
      dateFormat.format(calendar.getTime)
    }
  }
  
  def format( formatString : String) : String = {
    new java.text.SimpleDateFormat(formatString).format(calendar.getTime)
  }

}


object TimeUnit{

  val Second = 1000L
  val Minute = 60 * Second
  val Hour = 60 * Minute
  val Day = 24 * Hour
  val Year = 365 * Day
  val LeapYear = 366 * Day
  
}