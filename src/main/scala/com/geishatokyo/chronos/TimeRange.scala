package com.geishatokyo.chronos



class TimeRange( begin : DateTime , end : DateTime){

  def step( duration : Duration) : Stream[DateTime] = {
    if(begin <= end){
      begin #:: new TimeRange(begin + duration,end).step(duration)
    }else{
      Stream.Empty
    }
  }
  
  def contains( time : DateTime) = begin <= time && time <= end
  
  

}
