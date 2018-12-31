package spath.testing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import spath.graphs.DirectedGraph;
import spath.graphs.Edge;
import spath.graphs.Vertex;

/**
 * Class to generate a graph with a predetermined weighted shortest path
 * to be used to test students' Dijkstra's Algorithm Implementations
 * 
 * @author timhuber
 *
 */
public class GraphGenerator {
	private final int numVertices;
	private final int numEdges;
	private final int shortestPathEdges;
	private final HashMap<Vertex, Integer> distFromStart;
	private final HashMap<Vertex, Integer> distFromEnd;
	private final Random r = new Random();
	private int vertCount;
	private int edgeCount;
	
	private final LinkedList<Edge> shortestPath;
	private int shortestPathValue = 0;
	private final DirectedGraph graph;
	private final HashMap<Edge, Integer> weights;
	private Vertex endVertex;
	private Vertex startVertex;

	


	/**
	 * 
	 * @param vertices the number of vertices in the graph to be generated
	 * @param edges the number of edges in the graph to be generated
	 * @param shortestPathEdges the number of edges in the shortest path 
	 *        to be generated
	 */
	public GraphGenerator (int vertices, int edges, int shortestPathEdges) {
		this.numVertices = vertices;
		this.numEdges = edges;	
		this.shortestPathEdges = shortestPathEdges;
		this.graph = new DirectedGraph();
		this.weights = new HashMap<Edge, Integer>();
		this.shortestPath = new LinkedList<Edge>();
		this.distFromStart = new HashMap<Vertex, Integer>();
		this.distFromEnd = new HashMap<Vertex, Integer>();
				
	}
	
	/**
	 * Generate the graph using the parameters given in the constructor
	 * @return the generated graph
	 */
	public DirectedGraph create() {
		this.startVertex = new Vertex();
		//generate shortest path recursively
		shortestPathHelper(startVertex);
		
		while(vertCount < numVertices) {
			addEdgeWithVertex();
		}
		
		while(edgeCount < numEdges) {
			addEdge();
		}
		
		return graph;
	}
	
	/**
	 * 
	 * @return the graph with predetermined shortest path
	 */
	public DirectedGraph getGraph() {
		return graph;
	}
	
	/**
	 * 
	 * @return map of edges to their weights
	 */
	public HashMap<Edge, Integer> weights() {
		return weights;
	}
	
	/**
	 * 
	 * @return the first vertex in the predetermined shortest path
	 */
	public Vertex start() {
		return startVertex;
	}
	
	/**
	 * 
	 * @return the last vertex in the predetermined shortest path
	 */
	public Vertex end() {
		return endVertex;
	}
	
	/**
	 * @return a linkedlist of edges that make up the shortest path from vertex 0
	 * to the end vertex in our graph
	 */
	public LinkedList<Edge> getShortestPath() {
		return shortestPath;
	}
	
	/**
	 * 
	 * @return the sum of the weights of the edges that make up the shortest path
	 */
	public int getShortestPathValue() {
		return shortestPathValue;
	}
	
	/**
	 * 
	 * @return the number of vertices in the graph
	 */
	public int getNumVerts() {
		return vertCount;
	}
	
	/**
	 * 
	 * @return the number of edges in the graph
	 */
	public int getNumEdges() {
		return edgeCount;
	}
	
	/**
	 * Generate the shortest path with length shortestPathEdges that will remain 
	 * unchanged across any calls to addEdgeWithVertex() and addEdge(). The vertices in
	 * this shortest path will have id's 0,..., shortestPathEdges - 1
	 * 
	 * @return the vertex at the end of this shortest path
	 */
	public void genShortestPath() {
		//create a starting vertex and set its distFromStart to zero
		this.startVertex = new Vertex();
		distFromStart.put(startVertex, 0);
		
		shortestPathHelper(startVertex);
		
		//build up the distFromStart mapping after the helper method is finished
		for(int i = 0; i < shortestPath.size(); ++i) {
			Edge e = shortestPath.get(i);
			distFromStart.put(e.to, shortestPathValue - distFromEnd.get(e.to));
		}
	}

	/**
	 * recursive helper method that actually generates the shortest path
	 */
	private Vertex shortestPathHelper(Vertex source) {
		//
		//If we reach the end of the shortest path (there are shortestPathEdges + 1
		//vertices and shortestPathEdges edges) set the end vertex
		//
		if(vertCount >= shortestPathEdges) {
			distFromEnd.put(source, 0);
			endVertex = source;

			graph.addVertex(source);
			++vertCount;

			return source;
		}
		
		graph.addVertex(source);
		++vertCount;
		
		//
		//Recur until there are shortestPathEdges + 1 vertices, and then generate
		//new edges with random weights, building the shortest path from end to start
		//as calls are popped off the stack
		//
		Vertex target = shortestPathHelper(new Vertex());
		Edge e = new Edge(source, target);
		graph.addEdge(e);

		//generate a random weight for the edge between 1 and 10
		int weight = r.nextInt(10) + 1;
		shortestPathValue += weight;
		weights.put(e, weight);

		shortestPath.addFirst(e);
		++edgeCount;

		distFromEnd.put(source, distFromEnd.get(e.to) + weight);

		return source;
	}

	/**
	 * Method that adds a vertex along with two edges connecting it to existing 
	 * vertices in the graph. This method assigns edge weights such that the
	 * predesignated shortest path in the graph is unchanged.
	 */
	public void addEdgeWithVertex() {
		Vertex newVert = new Vertex();
		Vertex source = getRandomVertex();
		Vertex target = getRandomVertex();
		
		//
		//The start values assigned to each vertex indicate the shortest distance from
		//the first vertex (with id 0) to that vertex. We want to ensure that any new
		//edges added to the graph don't replace this existing shortest distance.
		//
		
		int dDist = distFromStart.get(target) - distFromStart.get(source);
		
		//
		//Set the total weight of the two edges to a random value slightly larger than dDist, or
		//to 2 if dDist is less than 2 to avoid negative and 0 weighted edges
		//
		int totalWeight = Math.max(dDist + r.nextInt(2) + 1, 2);
		
		int e1Weight = totalWeight / 2;
		int e2Weight = totalWeight - e1Weight;
		
		distFromStart.put(newVert, distFromStart.get(source) + e1Weight);
		distFromEnd.put(newVert, distFromEnd.get(target) + e2Weight);

		
		Edge e1 = new Edge(source, newVert);
		Edge e2 = new Edge(newVert, target);
		
		graph.addVertex(newVert);
		++vertCount;
		
		graph.addEdge(e1);
		graph.addEdge(e2);
		edgeCount += 2;
		
		weights.put(e1, e1Weight);
		weights.put(e2, e2Weight);
		
	}
	
	/**
	 * A method that adds an edge between any two vertices (including edges from a 
	 * vertex to itself) in the graph such that this new edge does not alter the 
	 * predetermined shortest path in the graph.
	 */
	public void addEdge() {
		Vertex source = getRandomVertex();
		Vertex target = getRandomVertex();
		
		int dDist = distFromStart.get(target) - distFromStart.get(source);
		
		int weight = Math.max(dDist + r.nextInt(2) + 1, 1);
		
		Edge e = new Edge(source, target);
		graph.addEdge(e);
		++edgeCount;
		weights.put(e, weight);
		
	}
	
	/**
	 * 
	 * @return a random vertex from the graph that is being generated
	 */
	private Vertex getRandomVertex() {
		int n = graph.getNumVertices();
		int which = r.nextInt(n);
		Iterator<Vertex> iv = graph.vertices().iterator();
		for (int i=0; i < n; ++i) {
			Vertex v = iv.next();
			if (i == which) {
				return v;
			}
			
		}
		throw new Error("cannot get here");
	}
	
}
