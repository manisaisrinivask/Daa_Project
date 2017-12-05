package com.example.usgir.emergencydispatcher;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    ArrayList<int[]> Available;
    int V=9;
    ArrayAdapter<String> adapter;
    ArrayList<String> zipcodes;
    int pos;
    int graph[][];
    ArrayList<Integer> flags;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String Status = " ";
    RadioGroup group;
    RadioButton button1,button2,button3;
    int type;
    int id;
    String vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (RadioGroup)findViewById(R.id.radioGroup);
        button1 = (RadioButton)findViewById(R.id.radioButton);
        button2 = (RadioButton)findViewById(R.id.radioButton2);
        button3 = (RadioButton)findViewById(R.id.radioButton3);
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Vehicle Status");
        builder.setCancelable(true);
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
        flags = new ArrayList<Integer>();
        graph = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        zipcodes = new ArrayList<String>();
        zipcodes.add("64110");
        zipcodes.add("64111");
        zipcodes.add("64152");
        zipcodes.add("64184");
        zipcodes.add("64220");
        zipcodes.add("64279");
        zipcodes.add("64130");
        zipcodes.add("64310");
        zipcodes.add("64252");
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,zipcodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
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
    void dijkstra(int graph[][], int src,ArrayList<Integer> flagsguy)
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
        printSolution(dist, V,flagsguy,src);
    }
    void printSolution(int dist[], int n,ArrayList<Integer> flags,int src)
    {
        int min=999;
        int minv = 0;
        int flag = 0;
        //System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
        {
            //System.out.println(i+" tt "+dist[i]);
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
        if(ar[type]>=1)
        {
            id = (src*100)+(type*10)+ar[type];
            //Toast.makeText(getApplicationContext(),"Vehicle Started from "+"'"+zipcodes.get(src)+"'"+"and is"+" 0 miles away from destination",Toast.LENGTH_SHORT).show();
            Status = vehicle+" '"+id+"'"+" Available from "+"'"+zipcodes.get(src)+"'"+"and is on it's way to destination";
            ar[type]--;
            show(Status);
            //builder.setMessage(Status);
        }
        else
        {
            id = (src*100)+(type*10)+ar[type];
            String stat1 = vehicle +" Not Available from "+zipcodes.get(src);
            //Toast.makeText(getApplicationContext(),"Vehicle Not Availabe from "+zipcodes.get(src),Toast.LENGTH_SHORT).show();
            //System.out.println("Nearest node to source is:  "+flags.get(0));
            for (int x = 0;x < flags.size();x++)
            {
                int l[] = Available.get(flags.get(x));
                if (l[type]>=1)
                {
                    id = ((flags.get(x))*100)+(type*10)+l[type];
                   //Toast.makeText(getApplicationContext(),"Vehicle Started from "+"'"+zipcodes.get(flags.get(x))+"'"+"and is"+dist[flags.get(x)]+" miles away from destination",Toast.LENGTH_SHORT).show();
                    Status = vehicle+" '"+id+"'" + "Started from "+"'"+zipcodes.get(flags.get(x))+"'"+"and is "+dist[flags.get(x)]+" miles away from destination";
                    stat1 = stat1+"\n\n"+Status;
                    l[type]--;
                    show(stat1);
                   // builder.setMessage(Status);
                    //dialog = builder.create();
                    //dialog.show();
                    break;
                }
                else
                {
                    id = ((flags.get(x))*100)+(type*10)+l[type];
                    //Toast.makeText(getApplicationContext(),vehicle+ "Not Availabe from "+zipcodes.get(flags.get(x)),Toast.LENGTH_SHORT).show();
                    Status = vehicle +" Not Availabe from "+zipcodes.get(flags.get(x));
                    stat1 = stat1 + "\n\n"+Status;
                }
            }
        }

    }
    void getveh(View view)
    {
        if (button1.isChecked())
        {
            type = 0;
            vehicle = "Ambulance";
            builder.setIcon(R.mipmap.ic_ambul);
        }
        if (button2.isChecked())
        {
            type = 1;
            vehicle = "Fire Truck";
            builder.setIcon(R.mipmap.ic_firev);
        }
        if (button3.isChecked())
        {
            type = 2;
            vehicle = "Police Car";
            builder.setIcon(R.mipmap.ic_pol);
        }
        dijkstra(graph, pos,flags);
        //builder.setMessage(Status);
        //dialog = builder.create();
        //dialog.show();
    }
    void show(String msg)
    {
        builder.setMessage(msg);
        dialog = builder.create();
        dialog.show();
    }
}
