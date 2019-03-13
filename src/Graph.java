
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Queue;


public class Graph 
{
    static TreeSet<Node>settledSet = new TreeSet<Node>();
	static LinkedHashMap<String, Node> verticesMap = new LinkedHashMap<String, Node>();
	static PriorityQueue<Node> verticesQueue = new PriorityQueue<Node>();
	
	public static void main(String[]args) 
	{
		
		Node node1;
		Node node2;
		int weight;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter two nodes and the weight between them. Enter a semicolon to stop.");

		while(!keyboard.nextLine().equals(";"))
		{
			System.out.println("Enter vertex 1.");
			String n1 = keyboard.nextLine();
			System.out.println("Enter vertex 2.");
			String n2 = keyboard.nextLine();
			
			System.out.println("Enter weight between those vertices - no negative numbers.");
			weight = Integer.parseInt(keyboard.nextLine());
			
			if (weight >= 0)
			{   
				if(verticesMap.containsKey(n1))
					node1 = verticesMap.get(n1);
				else
					node1 = new Node(n1);
			
				if(verticesMap.containsKey(n2))
					node2 = verticesMap.get(n2);
				else
					node2 = new Node(n2);
				
				verticesQueue.remove(node1);
				verticesQueue.remove(node2);
				node1.addEdge(node2, weight);
				node2.addEdge(node1, weight);
				verticesMap.put(node1.getName(),node1);
				verticesMap.put(node2.getName(),node2);
				verticesQueue.add(node1);
				verticesQueue.add(node2);
			}
		}
		
		System.out.println("Enter a vertex name to see the weight from that vertex to everything else");
		String vName = keyboard.nextLine();
		keyboard.close();
		
		Node source = verticesMap.get(vName);
	    //perform di's alg on source
		verticesQueue.remove(source);
		source.changeD(0);
	    source.setPath("");
		verticesQueue.add(source);
		while(!verticesQueue.isEmpty())
		{
			Node currNode = verticesQueue.poll();
			if(!currNode.colored) 
			{
				for(Entry<Node,Integer> adjacent : currNode.getWeightedAdjacents().entrySet()) 
				{   //change distance from source and prev if total weight is smaller than d.
					Node neighbor = adjacent.getKey();
					int neighborWeight = (int)adjacent.getValue();
						
					if(neighborWeight + currNode.getD() < neighbor.getD()) 
					{  
						verticesQueue.remove(neighbor);
						neighbor.changeD(neighborWeight + currNode.getD());
						neighbor.setPath(currNode.getPath());
						verticesQueue.add(neighbor);
					}	
				}
				currNode.color();
				settledSet.add(currNode);    
			}
		}
		//display final results
		while(!settledSet.isEmpty()) 
		{
			Node nodeN = settledSet.pollFirst();
			if (nodeN.equals(source))
				continue;
			System.out.println("Distance to "+nodeN.getName()+" from "+ source.getName()+" is "+nodeN.getD()+" and path is"+nodeN.getPath());
		}
	}

	
}

