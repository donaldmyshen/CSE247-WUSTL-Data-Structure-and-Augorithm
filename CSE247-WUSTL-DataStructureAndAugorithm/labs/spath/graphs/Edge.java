package spath.graphs;

/**
 * Edge object for the directed multigraph DirectedGraph
 * 
 * @author timhuber
 *
 */
public class Edge {
	
	public final Vertex from, to;
	
	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to   = to;
	}
	
	public String toString() {
		return "Edge " + from + "-->" + to;
	}

}
