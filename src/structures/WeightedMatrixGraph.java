package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a weighted graph using a matrix
 * @author Cristian Sanchez
 * @author Juan Pablo Herrera
 * @author Alejandro Parra
 * @version 1.0 - 11/2019
 * @param <V> Abstract data type that represents the object to be modeled in the graph
 */
public class WeightedMatrixGraph<V> implements Graph<V> {
	
	/**
	 * Constant used to represent the default amount of possible vertices
	 */
	public final static int DEFAULT_SIZE = 10;
	
	/**
	 * Map that stores the vertices and gives them a unique index
	 */
	private Map<V,Integer> vertices;
	private Map<Integer,V> invVertices;
	
	/**
	 * Weighted matrix used to represent the graph
	 */
	private int[][] Weight;
	private int[][] minPath;
	
	/**
	 * atribute used to represent if the grpah is directed or not
	 */
	public boolean isDirected;
	
	/**
	 * Constructor that creates an object of the type WeightedMatrixGraph with a default amount of vertices
	 * @param d directed or not
	 */
	public WeightedMatrixGraph(boolean d) {
		vertices = new HashMap<V, Integer>();
		invVertices = new HashMap<Integer, V>();
		Weight = new int[DEFAULT_SIZE][DEFAULT_SIZE];
		isDirected = d;
	}
	
	/**
	 * Constructor that creates an object of the type WeightedMatrixGraph with a selected amount of vertices
	 * @param v Amount of vertices
	 * @param d directed or not
	 */
	public WeightedMatrixGraph(int v, boolean d) {
		vertices = new HashMap<V, Integer>();
		invVertices = new HashMap<Integer, V>();
		Weight = new int[v][v];
		isDirected = d;
	}

	@Override
	public boolean addVertex(V v) {
		vertices.put(v, vertices.size());
		
		return true;
	}

	@Override
	public boolean addEdge(V v, V u) {
		int posv = vertices.get(v);
		int posu = vertices.get(u);
		
		Weight[posv][posu] = 1;
		
		if(!isDirected) Weight[posu][posv] = 1;
		
		return true;
	}

	@Override
	public boolean addEdge(V v, V u, int w) {
		int posv = vertices.get(v);
		int posu = vertices.get(u);
		
		Weight[posv][posu] = w;
		
		if(!isDirected) Weight[posu][posv] = w;
		
		return true;
	}

	@Override
	public void removeVertex(V v) {
	}

	@Override
	public void removeEdge(V v, V u) {
		int posv = vertices.get(v);
		int posu = vertices.get(u);
		
		Weight[posv][posu] = Integer.MAX_VALUE;
		
		if(!isDirected) Weight[posu][posv] = Integer.MAX_VALUE;
	}

	@Override
	public boolean areConnected(V v, V u) {
		int posv = vertices.get(v);
		int posu = vertices.get(u);
		
		return (Weight[posv][posu] != Integer.MAX_VALUE && posu != posv)? true : false;
	}

	@Override
	public List<V> bfs(V v) {
		return (vertices.size() != 0)? Algorithms.bfs(this, invVertices.get(0)) : null;
	}

	@Override
	public List<V> dfs(V v) {
		return (vertices.size() != 0)? Algorithms.dfs(this, invVertices.get(0)) : null;
	}

	@Override
	public int[] dijkstra(V v) {
		return (vertices.size() != 0)? Algorithms.dijkstra(Weight, vertices.get(v)) : null;
	}

	@Override
	public int[][] floydWarshall() {
		return (vertices.size() != 0)? Algorithms.floydWarshall(Weight) : null;
	}
	
	@Override
	public String floydWarshall2(V v, int start, int end) {
		return (vertices.size() != 0)? Algorithms.floydWarshall2(this, v, start, end) : null;
	}
	
	@Override
	public int floydWarshall3(V v, int start, int end) {
		return (vertices.size() != 0) ? Algorithms.floydWarshall3(this, v, start, end) : null;
	}

	@Override
	public int getVertexSize() {
		return vertices.size();
	}

	@Override
	public int getIndex(V vertex) {
		return vertices.get(vertex);
	}

	@Override
	public List<V> vertexAdjacent(V vertex) {
		List<V> adjacent = new ArrayList<V>();
		int pos = vertices.get(vertex);
		
		for(int i=0; i<Weight.length; i++) {
			if(Weight[pos][i] != Integer.MAX_VALUE && pos != i) adjacent.add(invVertices.get(i));
		}
		
		return adjacent;
	}

	@Override
	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public Map<V, Integer> getVertices() {
		return vertices;
	}

	@Override
	public int[][] Kruskal(int[][] p) {
		return (vertices.size() != 0)? Algorithms.Kruskal(Weight) : null;
	}

	@Override
	public int[] Prim(int[][] p) {
		return (vertices.size() != 0)? Algorithms.prim(Weight) : null;
	}
	
	@Override
	public int[][] getWeight() {
		return Weight;
	}

	@Override
	public Map<Integer, List<Integer>> dijkstra2(V v) {
		return (vertices.size() != 0)? Algorithms.dijkstra2(this, v) : null;
	}

	@Override
	public DisjointSet<Integer> Kruskal2(int[][] p) {
		return (vertices.size() != 0)? Algorithms.Kruskal2(Weight) : null;
	}

}
