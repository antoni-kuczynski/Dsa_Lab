package pl.edu.pw.ee.aisd2025zex7.graphviz;

import java.io.*;
import java.util.*;

public class MatrixToGraphviz {
    private static final String BELLMAN_FORD_SLOWEST = "src/test/resources/data/graph/bellmanford/slowest_full.txt";
    private static final String BELLMAN_FORD_FASTEST = "src/test/resources/data/graph/bellmanford/fastest_star_type.txt";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(
                BELLMAN_FORD_FASTEST
        ));

        String type = br.readLine().trim();

        boolean directed = type.equalsIgnoreCase("directed");

        String[] size = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String s1 = br.readLine();
            if (s1 == null)
                continue;
            String[] parts = s1.trim().split("\\s+");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            edges.add(new Edge(a, b, w));
        }

        StringBuilder dot = new StringBuilder();

        if (directed) {
            dot.append("digraph G {\n");
        } else {
            dot.append("graph G {\n");
        }

        String arrow = directed ? " -> " : " -- ";

        for (Edge e : edges) {
            dot.append("    ")
                    .append(e.a)
                    .append(arrow)
                    .append(e.b)
                    .append(" [label=\"")
                    .append(e.w)
                    .append("\"];\n");
        }

        dot.append("}\n");

        System.out.println(dot);
    }

    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
}
