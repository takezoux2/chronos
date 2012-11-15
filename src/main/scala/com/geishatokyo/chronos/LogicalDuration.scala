package com.geishatokyo.chronos

import java.util.Calendar._


case class Year(value : Int) extends TDuration with SingleTimeField{
  val fieldId = YEAR
  
  def loaf = null
}
