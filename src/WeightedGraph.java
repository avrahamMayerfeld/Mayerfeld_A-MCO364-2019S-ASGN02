
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Queue;


public class WeightedGraph 
{
   
	static LinkedList<Node> settledQueue = new LinkedList<Node>();
    	static LinkedHashMap<String, Node> verticesMap = new LinkedHashMap<String, Node>();
	
	public static void main(String[]args) 
	{
		
		PriorityQueue<Node> verticesQueue = new PriorityQueue<Node>();
		
		Scanner keyboard = new Scanner(System.in);
		Node node1;
		Node node2;
		String input1;
		String input2;
		int weight;
		System.out.println("Enter two nodes and the weight between them. Begin by pressing enter. Enter a semicolon to finish.");
		while(!keyboard.nextLine().equals(";"))
		{
			System.out.println("Enter vertex 1.");
			input1 = keyboard.nextLine();
			System.out.println("Enter vertex 2.");
			input2 = keyboard.nextLine();
			System.out.println("Enter weight between those vertices - no negative numbers allowed.");
			weight = Integer.parseInt(keyboard.nextLine());
			
			if (weight >= 0)
			{   
				if(verticesMap.containsKey(input1))
					node1 = verticesMap.get(input1);
				else
					node1 = new Node(input1);
			
				if(verticesMap.containsKey(input2))
					node2 = verticesMap.get(input2);
				else
					node2 = new Node(input2);
				
				node1.addEdge(node2, weight);
				node2.addEdge(node1, weight);
				verticesMap.put(node1.getName(),node1);
				verticesMap.put(node2.getName(),node2);
				
			}
		}
		
		System.out.println("Enter a vertex name to see the shortest path from that vertex to everything else");
		String sourceName = keyboard.nextLine();
		keyboard.close();
		
		Node source = verticesMap.get(sourceName);
		Node currNode = null;
		Node neighbor = null;
		int neighborWeight = 0;
	    	//perform di's alg on source
		//bgverticesQueue.remove(source);
		source.changeD(0);
	    	source.setPath("");
		verticesQueue.add(source);
		
		while(!verticesQueue.isEmpty())
		{
			currNode = verticesQueue.poll();
			if(!currNode.colored)
			{
				for(Entry<Node,Integer> adjacent : currNode.getWeightedAdjacents().entrySet()) 
				{   
					//change distance from source and prev if total weight is smaller than d.
					neighbor = adjacent.getKey();
					neighborWeight = adjacent.getValue();
					
					if(neighborWeight + currNode.getD() < neighbor.getD()) 
					{   
						verticesQueue.remove(neighbor);
						neighbor.changeD(neighborWeight + currNode.getD());
						neighbor.setPath(currNode.getPath());
						verticesQueue.add(neighbor);
					}	
				}
				currNode.color();
				settledQueue.add(currNode);    
			}
		}
		//display final results
		while(!settledQueue.isEmpty()) 
		{
			Node nodeN = settledQueue.poll();
			if (nodeN.equals(source))
				continue;
			System.out.println("Shortest distance to "+nodeN.getName()+" from "+ source.getName()+" is "+nodeN.getD()+" and that path is"+nodeN.getPath());
		}
	}

	
}

