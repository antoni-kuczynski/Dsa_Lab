package pl.edu.pw.ee.aisd2025zex7.converter;

import java.io.*;
import java.util.*;

public class CitiesToGraphviz {

    public static void convertToGraphviz(String inputFile, String outputFile) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        List<Edge> edges = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String cityA = parts[0];
            String cityB = parts[2];
            int weight = Integer.parseInt(parts[3]);

            edges.add(new Edge(cityA, cityB, weight));
        }

        br.close();

        out.println("graph Cities {");
        out.println("    layout=neato;");
        out.println("    overlap=false;");
        out.println("    splines=true;");
        out.println("    node [shape=circle, fontsize=12];");
        out.println("    graph [bgcolor=\"transparent\", background=\"\"];");
        out.println();

        for (Edge e : edges) {
            out.printf("    \"%s\" -- \"%s\" [label=%d];%n",
                    e.cityA, e.cityB, e.weight);
        }

        out.println("}");
        out.close();
    }

    // https://www.mapcustomizer.com/map/stolice_aisd_cw7_Xh%266(*(*%26JKBhghkj
    public static void main(String[] args) throws Exception {
        String inputFile = "stolice.txt";
        String outputFile = "stolice.dot";

        convertToGraphviz(inputFile, outputFile);

        ProcessBuilder pb = new ProcessBuilder(
                "neato",
                "-Tpng",
                outputFile,
                "-o",
                "stolice.png"
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("error: " + exitCode);
        }
    }


    static class Edge {
        String cityA, cityB;
        int weight;

        Edge(String a, String b, int w) {
            this.cityA = a;
            this.cityB = b;
            this.weight = w;
        }
    }
}
