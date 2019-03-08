import java.util.*;

//input: an int, represents a mean of minutes. 
//return: a random time. exponentially distributed around the mean. 
public class RandBox extends Random{

	
	static double expo( double mean)
    {
      double x = Math.random();
      x = -mean * Math.log(x);
      return x;
    }
	
}
