public class ClosestPairNaive {
    
    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    
    //
    // findClosestPair()
    //
    // Given a collection of nPoints points, find and ***print***
    //  * the closest pair of points
    //  * the distance between them
    // in the form "(x1, y1) (x2, y2) distance"
    //
    
    // INPUTS:
    //  - points sorted in nondecreasing order by X coordinate
    //  - points sorted in nondecreasing order by Y coordinate
    //
    
    public static void findClosestPair(XYPoint points[])
    {	
   double minDist = Double.POSITIVE_INFINITY;	
   XYPoint p1 = null;
   XYPoint p2 = null;
   int k;
   int j=0;
   
   //iterate through all points to find the minimum distance
   while(j <= points.length-2){
	   k = j+1; 
	   while (k <= points.length-1) {
		   double distance = points[j].dist(points[k]);
		   if (distance < minDist) {
			   minDist = distance; 
			   p1 = points[j];
			   p2 = points[k];
		   }
		   k++;
	   }
	   j++;
   }
   //print the point with lowest X-coordinate first;
   //if the two points have equal X-coordinates, print the point with lowest Y-coordinate first;
   if ((p2.x > p1.x) || ( (p1.x == p2.x) && (p2.y > p1.y) ) || ( (p1.x == p2.x) && (p1.y == p2.y) )){
	   System.out.println(p1 + " " + p2 + " " + minDist + " "); 
   }
   if ((p1.x > p2.x) || ( (p1.x == p2.x) && (p1.y > p2.y ) )){
	   System.out.println(p2 + " " + p1 + " " + minDist + " "); 
   }
	
    }
}
