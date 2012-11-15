package com.geishatokyo.chronos

import org.specs2.mutable.Specification
import java.util.{Date,Calendar}
import Implicits._

class UsageTest extends Specification{
  import AvoidSpecs2ImplicitConversions._

  "Duration" should{
    "compose" in {
      durationShouldCompose
      ok
    
    }
    
  }
  
  "Time field" should{
    "set values" in{
      timeFieldShouldSet
      ok
    }
  }
  
  "for" should{
    "days" in {
      forShouldStepDays
      ok
    }
  }
}


object AvoidSpecs2ImplicitConversions{

  def durationShouldCompose = {
      val d = DateTime.now
      
      val d2 = d + (2.hours * 2 + 30.minutes)  
      
      d.minutes
  }
  
  def timeFieldShouldSet = {
  
      val d = DateTime.now
      
      d.set( 5 oclock)
      
      import Month._
      d.set( May / 5)
      
      d.set(4 月,4 日, 5 時, 3 分)
      
      println("$$$" + (d next(May / 5)))
  }
  
  def forShouldStepDays {
    val d = DateTime.now
    for( day <- d until (d + 5.days) step 1.days){
      println(day)
    }
    
    for( day <- d to (d + 5.days) step 1.days){
      println(day)
    }
  }
}




