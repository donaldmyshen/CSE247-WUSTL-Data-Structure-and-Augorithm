package studio8;

import java.io.IOException;
import java.util.HashSet;

public class HashPoints {
	
	public static void main(String[] args) throws IOException {
		
		//Here we will create a HashSet of Points.
		HashSet<Point> pointSet = new HashSet<Point>();
		
		Point p1 = new Point (1, 2);
		Point p2 = new Point (1, 2);

		Point p3 = new Point (3, 4);
		Point p4 = new Point (3, 4);
		
		Point p5 = new Point (-1, -6);
		Point p6 = new Point (-1, -6);

		//add points to the set
		pointSet.add(p1);
		pointSet.add(p2);
		pointSet.add(p3);
		pointSet.add(p4);
		pointSet.add(p5);
		pointSet.add(p6);

		System.out.println("pointSet has " + pointSet.size() + " elements in it.");
		System.out.println(pointSet);
	}

}
