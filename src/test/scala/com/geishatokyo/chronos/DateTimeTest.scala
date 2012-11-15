package com.geishatokyo.chronos

import org.specs2.mutable.Specification
import java.util.{Date,Calendar}
import Implicits._

class DateTimeTest extends Specification{

  "hoge" should{
    "hoge" in {
    
    
      val cal = Calendar.getInstance
      cal.set(2012,0,1)
      
      val year =  cal.get(Calendar.YEAR)
      
      println(year)
      
      val dt = DateTime.fromCalendar(cal)
      
      println(dt.year)
      
      dt.millis must_== cal.get(Calendar.MILLISECOND)
      dt.seconds must_== cal.get(Calendar.SECOND)
      dt.minute must_== cal.get(Calendar.MINUTE)
      dt.year must_== cal.get(Calendar.YEAR)
      dt.dayOfYear must_== cal.get(Calendar.DAY_OF_YEAR)
      dt.month must_== cal.get(Calendar.MONTH) + 1
      dt.dayOfMonth must_== cal.get(Calendar.DAY_OF_MONTH)
    }
    
    "fuga" in {
      import Implicits._
      val dt = DateTime.now
      val dt2 = dt + new TimeUnitWrapper(5).hours * 2//5.hours
      
      val diff = dt2 - dt
      
      diff.hours must_== 10
      
    }
  }
}
