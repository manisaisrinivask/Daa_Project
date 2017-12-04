import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
 
class ShortestPath
{
	static ArrayList<int[]> Available;
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V=9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n,ArrayList<Integer> flags,int src, int typ)
    {
    	int min=999;
    	int minv = 0;
    	int flag = 0;
        
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++)
        {
            System.out.println(i+" tt "+dist[i]);
            	if(dist[i]<min&&dist[i]!=0)
            	{
            		
            		min = dist[i];
            		minv = i;
            	}
            }  
        flags.add(minv);
        while(flags.size()<V)
        {
        	min = 999;
        for (int i = 0; i < V; i++)
        {
        	  flag = 0;
           // System.out.println(i+" tt "+dist[i]);
            	if(dist[i]<min&&dist[i]!=0)
            	{
            		for(int z = 0;z<flags.size();z++)
            		{
            			if (i == flags.get(z))
            			{
            				flag = 1;
            			}         			
            		}
            		if(flag==0)
            		{
            			min = dist[i];
                		minv = i;
            		}
            		
            	}
            }  
        flags.add(minv);
        }
        int ar[] = Available.get(src);
        if(ar[typ+1]>=1)
        {
        	System.out.println("Vehicle Available from "+"'"+src+"'"+"and is on it's way to destination");
        	ar[typ+1]--;
        }
        else
        {
        System.out.println("Nearest node to source is:  "+flags.get(0));
        for (int x = 0;x < flags.size();x++)
        {      
        int l[] = Available.get(flags.get(x));
        if (l[1]>=1)
        {
        	System.out.println("Vehicle Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination");
        	l[1]--;
        	break;
        }
        else
        {
        	System.out.println("Vehicle Not Availabe from "+flags.get(x));
        }
        }
        }
        
}
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src,ArrayList<Integer> flagsguy,int typ)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // print the constructed distance array
        printSolution(dist, V,flagsguy,src,typ);
    }
 
    // Driver method
    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
        int src,typ;
        Available = new ArrayList<int[]>();
        int i[] = {2,0,1};
        Available.add(0,i);
        int i2[] = {2,1,0};
        Available.add(1,i2);
        int i3[] = {2,1,2};
        Available.add(2,i3);
        int i4[] = {2,1,1};
        Available.add(3,i4);
        int i5[] = {2,2,2};
        Available.add(4,i5);
        int i6[] = {1,0,2};
        Available.add(5,i6);
        int i7[] = {2,1,0};
        Available.add(6,i7);
        int i8[] = {1,2,0};
        Available.add(7,i8);
        int i9[] = {1,0,1};
        Available.add(8,i9);
        ArrayList<Integer> flags = new ArrayList<Integer>();
       int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                  {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                  {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                  {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                  {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                  {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                  {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                  {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                  {0, 0, 2, 0, 0, 0, 6, 7, 0}
                                 };
        ShortestPath t = new ShortestPath();
        Scanner in = new Scanner(System.in);
        while(true)
        {
        System.out.println("enter Source");
        src=in.nextInt();
        System.out.println("enter type fire:1 Police:2 Ambulance:3");
        typ=in.nextInt();
        t.dijkstra(graph, src,flags,typ);
        }
    }
}