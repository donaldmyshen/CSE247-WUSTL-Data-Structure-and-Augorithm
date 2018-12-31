//
// VERTEX.JAVA
// Vertex object for DirectedGraph
//

package spath.graphs;

import java.util.ArrayList;

public class Vertex {
    
    // counter shared by all objects of this class -- incremented
    // every time we allocate a new Vertex
    private static int count = 0;
    
    // edges *out of* this vertex
    final private ArrayList<Edge> successors;
    
    // edges *into* this vertex
    final private ArrayList<Edge> predecessors;
    
    // unique identifier for vertex; used only for pretty-printing
    final private int id;
    
    public Vertex() {
    	this.id = ++count;
    	this.successors   = new ArrayList<Edge>();
    	this.predecessors = new ArrayList<Edge>();
    }
    
    // add an outgoing edge to the vertex's adjacency list
    public void addEdgeFrom(Edge e) {
    	if (this.equals(e.from)) {
    		successors.add(e);
    	}
    	else throw new Error("Bad edge " + e);
    }
    
    // add an incoming edge to the vertex's adjacency list
    public void addEdgeTo(Edge e) {
    	if (this.equals(e.to)) {
    		predecessors.add(e);
    	}
    	else throw new Error("Bad edge " + e);
    }
    
    // return an iterator over the vertex's outgoing edges
    public Iterable<Edge> edgesFrom() {
    	return successors;
    }
    
    // return an iterator over the vertex's incoming edges
    public Iterable<Edge> edgesTo() {
    	return predecessors;
    }
    
    public String toString() {
    	return "Vertex " + id;
    }
}
