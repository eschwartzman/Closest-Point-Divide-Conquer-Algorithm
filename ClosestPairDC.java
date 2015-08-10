import java.util.ArrayList;

public class ClosestPairDC {

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


	public static pointsAndDist findClosestPair1(XYPoint pointsByX[], 
			XYPoint pointsByY[]){

		if(pointsByX.length == 1){
			return  new pointsAndDist(pointsByX[0], pointsByX[0], Double.POSITIVE_INFINITY);
		}
		if(pointsByX.length == 2){
			return new pointsAndDist(pointsByX[0], pointsByX[1], pointsByX[0].dist(pointsByX[1]));
		}

		int midpoint = (pointsByX.length/2);

		XYPoint[] XL = java.util.Arrays.copyOfRange(pointsByX, 0, midpoint);
		XYPoint[] XR = java.util.Arrays.copyOfRange(pointsByX, midpoint, pointsByX.length);

		XYPoint[] YL = new XYPoint[midpoint];
		XYPoint[] YR = new XYPoint[pointsByY.length - midpoint];

		int yTrackerLeft = 0;
		int yTrackerRight = 0;
		int m = 0;
		while (m < pointsByX.length){
			if (pointsByY[m].isLeftOf(pointsByX[midpoint])){
				YL[yTrackerLeft] = pointsByY[m];
				yTrackerLeft++;
			}else{
				YR[yTrackerRight] = pointsByY[m];
				yTrackerRight++;
			}
			m++;
		}

		pointsAndDist distL = findClosestPair1(XL, YL);
		pointsAndDist distR = findClosestPair1(XR, YR);

		XYPoint mPoint = pointsByX[midpoint];
		double lrDist = Math.min(distL.distance, distR.distance);

		//Construct yStrip	

		ArrayList<XYPoint> yStrip = new ArrayList<XYPoint>();

		int c = 0;
		while(c < pointsByY.length){
			if (Math.abs(pointsByY[c].x - mPoint.x) < lrDist){
				yStrip.add(pointsByY[c] );
			}
			c++;
		}

		pointsAndDist answer;
		double minDist = lrDist;

		//The only distance that matters is the smallest one, that distance becomes the distance for the answer
		if(minDist == distL.distance){
			answer = distL;
		}else{ 
			answer = distR;
		}

		//Now see if there are two points with a smaller distance within yStrip
		for (int j =0; j <yStrip.size(); j++){
			int k=j+1;
			while ((k < yStrip.size()) && (yStrip.get(k).y - yStrip.get(j).y <= lrDist)){
				double dst = yStrip.get(j).dist(yStrip.get(k));
				if (dst<minDist){
					minDist = dst;
					answer = new pointsAndDist (yStrip.get(j), yStrip.get(k), dst);
				}
				k++;
			}
		}
		//This is the final answer
		return answer;

	}

	public static void findClosestPair(XYPoint pointsByX[], 
			XYPoint pointsByY[]){

		pointsAndDist finalAnswer = findClosestPair1(pointsByX, pointsByY);
		//print the point with lowest X-coordinate first;
		//if the two points have equal X-coordinates, print the point with lowest Y-coordinate first; 
		if ((finalAnswer.point2.x > finalAnswer.point1.x) || ( (finalAnswer.point1.x == finalAnswer.point2.x) 
				&& (finalAnswer.point2.y > finalAnswer.point1.y) ) || ( (finalAnswer.point1.x == finalAnswer.point2.x) && (finalAnswer.point1.y == finalAnswer.point2.y) )){
			System.out.println(finalAnswer.point1 + " " + finalAnswer.point2 + " " + finalAnswer.distance); 
		}
		if ((finalAnswer.point1.x > finalAnswer.point2.x) || ( (finalAnswer.point1.x == finalAnswer.point2.x) && (finalAnswer.point1.y > finalAnswer.point2.y) )){
			System.out.println(finalAnswer.point2 + " " + finalAnswer.point1 + " " + finalAnswer.distance); 
		}
	}
}
