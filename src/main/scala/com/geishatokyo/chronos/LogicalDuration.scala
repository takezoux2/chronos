package com.geishatokyo.chronos

import java.util.Calendar._

trait LogicalDuration{


}


case class Month( value : Int) extends LogicalDuration with SingleTimeField{
  val fieldId = MONTH
}
case class Year(value : Int) extends LogicalDuration with SingleTimeField{
  val fieldId = YEAR
}