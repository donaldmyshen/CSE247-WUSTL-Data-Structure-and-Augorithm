//
// VERTEXANDDIST.JAVA
// A pair containing a vertex and an estimate of its distance from
// a source.  Pairs may be compared based on their distances.
//
// Note that a pair, once constructed, may NOT be modified -- you need
// to create a new pair object if the estimated distance changes.
//

package spath;

import spath.graphs.Vertex;

public class VertexAndDist implements Comparable<VertexAndDist> {
	
	final public Vertex vertex;
	final public int distance;
	
	public VertexAndDist(Vertex v, int dist) {
		this.vertex   = v;
		this.distance = dist;
	}

       	@Override
	public String toString() {
		return "(" + vertex + ", " + distance + ")";
	}
 	
	//
	// Comparison of this and any other VertexAndDist object
	//   is based only on distance from source.  The MinHeap uses
	//   this information to find the object with the smallest 
	//   estimated distance at any given time.
	//

	@Override
	public int compareTo(VertexAndDist o) {
		Integer dist     = o.distance;
		Integer thisDist = this.distance;
		return thisDist.compareTo(dist);
	}
	
}
