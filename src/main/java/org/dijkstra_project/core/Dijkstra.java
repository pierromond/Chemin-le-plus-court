package org.dijkstra_project.core;

import org.dijkstra_project.run.WeightedGraph;

import org.gdms.data.SQLDataSourceFactory;
import org.gdms.data.types.Type;
import org.gdms.data.types.TypeFactory;
import org.gdms.data.values.Value;
import org.gdms.data.values.ValueFactory;

import org.gdms.sql.function.BasicFunctionSignature;

import org.gdms.sql.function.FunctionException;
import org.gdms.sql.function.FunctionSignature;
import org.gdms.sql.function.ScalarArgument;
import org.gdms.sql.function.AbstractScalarFunction;

public class Dijkstra extends AbstractScalarFunction {
    public class Dijkstra {

    // Dijkstra's algorithm to find shortest path from s to all other nodes
    public static int [] dijkstra (WeightedGraph G, int s) {
        final int [] dist_dijkstra = new int [G.size()];  // shortest known dist_dijkstraance from "s"
        final int [] pred = new int [G.size()];  // preceeding node in path
        final boolean [] visited = new boolean [G.size()]; // all false initially

        for (int i=0; i<dist_dijkstra.length; i++) {
            dist_dijkstra[i] = Integer.MAX_VALUE;
        }
        dist_dijkstra[s] = 0;

        for (int i=0; i<dist_dijkstra.length; i++) {
            final int next = minVertex (dist_dijkstra, visited);
            visited[next] = true;

            // The shortest path to next is dist_dijkstra[next] and via pred[next].

            final int [] n = G.neighbors (next);
            for (int j=0; j<n.length; j++) {
                final int v = n[j];
                final int d = dist_dijkstra[next] + G.getWeight(next,v);
                if (dist_dijkstra[v] > d) {
                dist_dijkstra[v] = d;
                pred[v] = next;
                }
            }
        }
        return pred;  // (ignore pred[s]==0!)
    }

    private static int minVertex (int [] dist_dijkstra, boolean [] v) {
        int x = Integer.MAX_VALUE;
        int y = -1;   // graph not connected, or no unvisited vertices
        for (int i=0; i<dist_dijkstra.length; i++) {
            if (!v[i] && dist_dijkstra[i]<x) {y=i; x=dist_dijkstra[i];}
        }
        return y;
    }

    public static void printPath (WeightedGraph G, int [] pred, int s, int e) {
        final java.util.ArrayList path = new java.util.ArrayList();
        int x = e;
        while (x!=s) {
            path.add (0, G.getLabel(x));
            x = pred[x];
        }
        path.add (0, G.getLabel(s));
        System.out.println (path);
    }

    }
    @Override
	public Value evaluate(SQLDataSourceFactory dsf, Value... args) throws FunctionException {
		if (args.length >3 ) {
			throw new FunctionException("Too many parameters !");
		} else {
			return ValueFactory.createValue(args[2].getAsDouble()
					);
		}
	}
    @Override
	public String getName() {
		return "Dijkstra";
	}

	@Override
	public Type getType(Type[] types) {
		return TypeFactory.createType(Type.DOUBLE);
	}

	
   
    @Override
    public FunctionSignature[] getFunctionSignatures() {
            return new FunctionSignature[] {
                    new BasicFunctionSignature(getType(null),
                    		ScalarArgument.INT,  // Frequency
							// [100,125,160,200,250,315,400,500,630,800,1000,1250,1600,2000,2500,3150,4000,5000]
                    		ScalarArgument.INT, // Category of road surface [Pervious, Non
							// Pervious]
                    		ScalarArgument.DOUBLE // Global SPL value (dBA)
                    		)
            };
    }
                    		
	@Override
	public String getDescription() {
		return "hophop";
	}

	@Override
	public String getSqlOrder() {
		return "hophop BR_SpectrumRepartition(100,1,dbA) as dbA_100 from myTable;";
	}
}