import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
import java.util.Random;
class EmergencyVehicleDispatch
{
	static ArrayList<int[]> Available;  //avaliability list
    //calculating shortest path for vetices not yet visited
    static final int V=13;
    int MDist(int dist[], Boolean Set[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (Set[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // function to print the solution
    void printSolution(int dist[], int n,ArrayList<Integer> flags,int src, int typ)
    {   Random rand = new Random();
        
        int  nb = rand.nextInt(10) + 1;
    	int min  = 999;
    	int minv = 0;
        int flag = 0;
       // Writer writer = null;
        
        for (int i = 0; i < V; i++)
        {
           //System.out.println(i+" tt "+dist[i]);
            	if(dist[i]<min)
            	{
            		
            		min = dist[i];
            		minv = i;
            	}
        }  
        //System.out.println(min);  
        flags.add(minv);
        while(flags.size()<V)
        {
        	min = 999;
        for (int i = 0; i < V; i++)
        {
        	  flag = 0;
           // System.out.println(i+" tt "+dist[i]);
            	if(dist[i]<min/*&&dist[i]!=0*/)// To find the closest node with requested emergency vehicle avaliable.
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
       
        // the loops are to iterate through the availability list and find the vehicle to be dispatched from the shortest distance node
        for (int x = 0;x < flags.size();x++)
        {
        
        int l[] = Available.get(flags.get(x));
        if (l[typ]>=1)
        {   if(typ==0){
            
            System.out.println("Vehicle F"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+"will arrive in "+dist[flags.get(x)]+"mins");
            l[typ]--;
            break;}
            if(typ==1){
               
                System.out.println("Vehicle P"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+"will arrive in "+dist[flags.get(x)]+"mins");
                l[typ]--;
                break;
            }
            else{
                //PrintWriter writer = new PrintWriter("Vehicle_Allocation.txt", "UTF-8");
                System.out.println("Vehicle A"+ x+nb +" Available from "+"'"+flags.get(x)+"'"+"and is on it's way to destination"+"will arrive in "+dist[flags.get(x)]+"mins");
                l[typ]--;
                break;
            }
        }
        else
        {
        	//System.out.println("Vehicle Not Availabe from "+flags.get(x));
        }
        
        }
        }
        
 
    //Nearestzip's algorithm
    void Nearestzip(int graph[][], int src,ArrayList<Integer> flagsguy,int typ)
    {
        int dist[] = new int[V]; 
        Boolean Set[] = new Boolean[V];
 
        // ISetting all othernodes except source to infinite
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            Set[i] = false;
        }
        dist[src] = 0;//the distance to source node is always 0
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            int u = MDist(dist, Set);
            Set[u] = true;
            for (int v = 0; v < V; v++)
                if (!Set[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        //calling print method
        printSolution(dist, V,flagsguy,src,typ);
    }
    //switch case for input of zipcodes.
    int zip(int src){
        int zipp=-1;
        
        switch (src) {
            case 64110:  zipp = 0;
                     break;
            case 64130:  zipp = 1;
                     break;
            case 64127:  zipp = 2;
                     break;
            case 64134:  zipp = 3;
                     break;
            case 64030:  zipp = 4;
                     break;
            case 64149:  zipp = 5;
                     break;
            case 64155:  zipp = 6;
                     break;
            case 64064:  zipp = 7;
                     break;
            case 64143:  zipp = 8;
                     break;
            case 64210: zipp = 9;
                     break;
            case 65311: zipp = 10;
                     break;
            case 61255: zipp = 11;
                     break;
            case 61232: zipp = 12;
                     break;
            default: zipp = 900000000;
                     break;
        }
        return zipp;
    }
    // Driver method
    public static void main (String[] args)
    {
        /* Vehicle avaliablity list*/
        int src,typ,src1;
        Available = new ArrayList<int[]>();
        int i1[] = {0,0,1};
        Available.add(0,i1);
        int i2[] = {1,0,0};
        Available.add(1,i2);
        int i3[] = {1,1,1};
        Available.add(2,i3);
        int i4[] = {2,0,1};
        Available.add(3,i4);
        int i5[] = {1,0,0};
        Available.add(4,i5);
        int i6[] = {0,2,0};
        Available.add(5,i6);
        int i7[] = {0,1,0};
        Available.add(6,i7);
        int i8[] = {3,0,0};
        Available.add(7,i8);
        int i9[] = {0,1,1};
        Available.add(8,i9);
        int i10[] ={1,0,0};
        Available.add(8,i10);
        int i11[] ={1,0,1};
        Available.add(8,i11);
        int i12[] ={0,0,0};
        Available.add(8,i12);
        int i13[] ={0,0,4};
        Available.add(8,i13);
        int deviceid[][] = new int[][]{{101,102,103,104,105,106,107,108,109,101},{201,202,203,204,205},{301,302,303,304,305,306,307,308,309}};
        ArrayList<Integer> flags = new ArrayList<Integer>();
        //The graph the zipcode nodes converted into multi dimentional array
       int graph[][] = new int[][]{
                                {0, 0, 0, 0, 6, 0, 0, 10, 8, 0, 0, 0, 0},
                                {0, 0, 7, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 7, 0, 0, 0, 9, 0, 0, 0, 6, 0, 0, 0},
                                {0, 11, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                                {6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 9, 2, 6, 0, 7, 0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 0, 7, 0, 0, 5, 0, 0, 0, 3},
                                {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {8, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                                {0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 2, 4, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 9, 0},
                                {0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 9, 0, 0},
                                {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
                                };
        EmergencyVehicleDispatch t = new EmergencyVehicleDispatch();
        Scanner in = new Scanner(System.in);
        while(true)
        {
        System.out.println("\n\nenter Source zipcode of avaliable \n1.64110 2.64130 3.64127 4.64134 5.64149 6.64155 \n7.64064 8.64143 9.64210 10.65311 11.61255 12.61232");
        src=in.nextInt();
        src1=t.zip(src);
        System.out.println("enter type fire:1 Police:2 Ambulance:3");
        typ=in.nextInt()-1;
        t.Nearestzip(graph,src1,flags,typ);
        }
    }
}