import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

	private static class Node<T> {
		private T label;
		private List<Node<T>> neighbors;
		
		public Node(T label) {
			this.label = label;
			neighbors = new ArrayList<Node<T>>();
		}
		
		private void addNeighbor(Node<T> node) {
			neighbors.add(node);
		}
		
		public String toString() {
			return label.toString();
		}
	}
	
	private Map<Node<T>, List<Node<T>>> graph;
	
	public Graph() {
		graph = new HashMap<>();
	}
	
	public void addVertex(T label) {
		//TODO check if such vertex already exists
		Node<T> vertexToAdd = new Node<>(label);
		graph.put(vertexToAdd, vertexToAdd.neighbors);
	}

	public Node<T> getVertex(T label) {
		for(Node<T> node : graph.keySet()) {
			if(node.label == label) {
				return node;
			}
		}
		return null;
	}
	
	public boolean addEdge(Node<T> v1, Node<T> v2) {
		//TODO check if edge already exists
		if(graph.containsKey(v1) && graph.containsKey(v2)) {
			v1.addNeighbor(v2);;
			v2.addNeighbor(v1);
			return true;
		} else {
			return false;
		}
	}
	
	public Set<Node<T>> getVertices() {
		return graph.keySet();
	}
	
	
	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>();
		graph.addVertex(2);
		graph.addVertex(10);
		graph.addVertex(5);
		graph.addEdge(graph.getVertex(2), graph.getVertex(10));
	}
}
