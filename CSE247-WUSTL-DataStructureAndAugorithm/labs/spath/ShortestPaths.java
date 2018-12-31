//
// SHORTESTPATHS.JAVA
// Compute shortest paths in a weighted, directed graph.
//

package spath;

import java.util.LinkedList;
import java.util.HashMap;

// heap-related structures from Lab 3
import heaps.Decreaser;
import heaps.MinHeap;

// directed graph structure
import spath.graphs.DirectedGraph;
import spath.graphs.Edge;
import spath.graphs.Vertex;

// vertex/dist pair for priority queue
import spath.VertexAndDist;

import timing.Ticker;


public class ShortestPaths {

    // "infinity" value for path lengths
    private final static Integer inf = Integer.MAX_VALUE;
    
    // a directed graph, and a weighting function on its edges
    private final DirectedGraph g;
    private HashMap<Edge, Integer> weights;	
    
    // starting vertex for shortest path computation
    private Vertex startVertex;
    
    // map from vertices to their handles into the priority queue
    private HashMap<Vertex, Decreaser<VertexAndDist>> handles;
    
    // map from vertices to their parent edges in the shortest-path tree
    private HashMap<Vertex, Edge> parentEdges;
    
    
    //
    // constructor
    //
    public ShortestPaths(DirectedGraph g, HashMap<Edge,Integer> weights, 
			 Vertex startVertex) {
    	this.g           = g; 
    	this.weights     = weights;

    	this.startVertex = startVertex;	
	
    	this.handles     = new HashMap<Vertex, Decreaser<VertexAndDist>>(); //Map
    	this.parentEdges = new HashMap<Vertex, Edge>();
    }

    
    //
    // run() 
    //
    // Given a weighted digraph stored in g/weights, compute a
    // shortest-path tree of parent edges back to a given starting
    // vertex.
    //
    public void run() {
    	// implementation method
    	Ticker ticker = new Ticker(); // heap requires a ticker
	
    	MinHeap<VertexAndDist> pq = 
    			new MinHeap<VertexAndDist>(g.getNumVertices(), ticker);
	
    	//
    	// Put all vertices into the heap, infinitely far from start.
    	// Record handle to each inserted vertex, and initialize
    	// parent edge of each to null (since we have as yet found 
    	// no path to it.)
    	//
    	for (Vertex v : g.vertices()) {
    		Decreaser<VertexAndDist> d = pq.insert(new VertexAndDist(v, inf));
    		handles.put(v, d);
    		parentEdges.put(v, null);
    	}
	
    	//
    	// Relax the starting vertex's distance to 0.
    	//   - get the handle to the vertex from the heap
    	//   - extract the vertex + distance object from the handle
    	//   - create a *new* vertex + distance object with a reduced 
    	//      distance
    	//   - update the heap through the vertex's handle
    	//
    	Decreaser<VertexAndDist> startHandle = handles.get(startVertex);
    	VertexAndDist vd = startHandle.getValue();
    	startHandle.decrease(new VertexAndDist(vd.vertex, 0));
	
    	//
    	// OK, now it's up to you!
    	// Implement the main loop of Dijkstra's shortest-path algorithm,
    	// recording the parent edges of each vertex in parentEdges.
    	// FIXME
    	//
    	handles.put(startVertex, startHandle);
		Vertex stv = startVertex;
		// BFS structure here 
		while(!pq.isEmpty()) {
			for(Edge E : stv.edgesFrom()) {
				int dist = handles.get(stv).getValue().distance + weights.get(E);
				Decreaser<VertexAndDist> EtoDist = handles.get(E.to);
				if(dist<=EtoDist.getValue().distance) {
					VertexAndDist vd1 = EtoDist.getValue();
					EtoDist.decrease(new VertexAndDist(vd1.vertex, dist));
					parentEdges.put(E.to, E);
				}
			}
				stv=pq.extractMin().vertex;
		}
	}
    //
    // returnPath()
    //
    // Given an ending vertex v, compute a linked list containing every
    // edge on a shortest path from the starting vertex (stored) to v.
    // The edges should be ordered starting from the start vertex.
    //
    public LinkedList<Edge> returnPath(Vertex endVertex) {
    	LinkedList<Edge> path = new LinkedList<Edge>();
	
    	//
    	// FIXME: implement this using the parent edges computed in run()
    	//
    	Vertex edv = endVertex;
		while(!edv.equals(startVertex)) {
			path.addFirst(parentEdges.get(edv));
			edv=parentEdges.get(edv).from;
		}
    	return path;
    }
    
    ////////////////////////////////////////////////////////////////
    
    //
    // returnLength()
    // Compute the total weight of a putative shortest path
    // from the start vertex to the specified end vertex.
    // No user-serviceable parts inside.
    //
    public int returnLength(Vertex endVertex) {
    	LinkedList<Edge> path = returnPath(endVertex);
	
    	int pathLength = 0;
    	for(Edge e : path) {
    		pathLength += weights.get(e);
    	}
	
    	return pathLength;
    }
}
