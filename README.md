Cronos is time library for scala.
It's just wrapper of Java's Calendar.



# Usage

## Access field


    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    
    val d = DateTime.now
    
    d.minutes
    d.hours
    d.dayOfWeek
    d.year
    ...

## Set

    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    
    val d = DateTime.now
    
    val d2 = d.set( 2 pm, 5 minute) // set 14:05
    

## Add and minus

    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    
    val d = DateTime.now
    
    val d2 = d + 2.days 
    
    val d3 = d - 1.years
    
    val d4 = d - 3.weeks
    
## Set just

    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    import com.geishatokyo.chronos.Month._ 
    
    val d = DateTime.now
    
    d.set( 2 oclock) // set 2:00 just.
    d.set( just(May / 2)) // set 5/5 0:00 just
    
## Diff

  
    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    
    val d : DateTime = new java.util.Date()
    val d2 = DateTime.now + 5.days
    
    val duration = d2 - d
    
    print(duration.days) // 5
    print(duration.hours) // 120
    
## For loop

    import com.geishatokyo.chronos.DateTime
    import com.geishatokyo.chronos.Implicits._ 
    
    val begin = DateTime(2000,1,1) + 5.minutes
    
    for( d <- begin until (begin + 5.days) step 1.days){
      println(d)
    }
    // will print
    // 2000/1/1 00:05:00.000
    // 2000/1/2 00:05:00.000
    // 2000/1/3 00:05:00.000
    // 2000/1/4 00:05:00.000
    // 2000/1/5 00:05:00.000
    