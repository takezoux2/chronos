package com.geishatokyo.chronos

import java.util.Calendar

case class Month( value : Int) extends TDuration with SingleTimeField{
  val fieldId = Calendar.MONTH
  
  def /(dayOfMonth : Int) = new MonthAndDay(value,dayOfMonth)
  
  def loaf = Year(1)
  
}

object Month{

  object January extends Month(1)
  def Jan = January
  
  object February extends Month(2)
  def Feb = February
  
  object March extends Month(3)
  def Mar = March
  
  object April extends Month(4)
  def Apr = April
  
  object May extends Month(5)
  
  object June extends Month(6)
  def Jun = June
  
  object July extends Month(7)
  def Jul = July
  
  object August extends Month(8)
  def Aug = August
  
  object September extends Month(9)
  def Sep = September
  
  object October extends Month(10)
  def Oct = October
  
  object November extends Month(11)
  def Nov = November
  
  object December extends Month(12)
  def Dec = December
  
  
  
}