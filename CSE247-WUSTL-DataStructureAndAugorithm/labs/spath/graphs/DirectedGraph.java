//
// DIRECTEDGRAPH.JAVA
//
// Implementation of a directed graph.  Technically, this is actually
// a *multigraph* because there can be multiple edges between the same
// two endpoints.
//

package spath.graphs;

import java.util.HashSet;

public class DirectedGraph {
	
	final private HashSet<Vertex> vertices;
	final private HashSet<Edge>   edges;
	
	public DirectedGraph() {
		this.vertices = new HashSet<Vertex>();
		this.edges    = new HashSet<Edge>();
	}
	
	public void addEdge(Edge e) {
		e.from.addEdgeFrom(e);
		e.to.addEdgeTo(e);
		edges.add(e);
	}
	
	public void addVertex(Vertex v) {
		this.vertices.add(v);
	}

	public int getNumEdges() {
		return edges.size();
	}
	
	public int getNumVertices() {
		return vertices.size();
	}
    
    // return an iterator over every vertex in the graph
	public Iterable<Vertex> vertices() {
		return vertices;
	}
	
    // return an iterator over every edge in the graph
	public Iterable<Edge> edges() {
		return edges;
	}
	
	public String toString() {
		return "Vertices " + vertices + " Edges " + edges;
	}
}
