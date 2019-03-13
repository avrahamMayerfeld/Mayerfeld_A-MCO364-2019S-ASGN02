import java.awt.List;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Node implements Comparable<Node> {

		
	LinkedHashMap<Node, Integer> weightedAdjacents = new LinkedHashMap<Node, Integer>();
	String name = null;
	boolean colored = false;
	
	int d = (int)Integer.MAX_VALUE;
	Node prevNode = null;
	
	
	public Node(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getD() {
		return d;
	}
	
	public void changeD(int newDee) {
		if(newDee < d)
			d = newDee;
	}
	
	public void setPrevNode(Node newNode) {
		prevNode = newNode;
	}
	public Node getPrevNode() {
		return prevNode;
	}
	
	public void color() {
		colored = true;
	}
	
	public void addEdge(Node adj, Integer w8) {
		weightedAdjacents.put(adj, w8);
	}
	
	public LinkedHashMap<Node, Integer> getWeightedAdjacents() {
		return weightedAdjacents;
	}
	//no good, if all maxValue never comes off queue?
	@Override
	public int compareTo(Node otherNode) {
		return Integer.compare(this.d, otherNode.getD());
	}
  
	
}
