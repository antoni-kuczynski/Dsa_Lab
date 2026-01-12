package pl.edu.pw.ee.aisd2025zex7.graphviz;

import java.io.*;
import java.util.*;

/**
 * Converts the "stolice.txt" file to graphviz .dot format and then generated png from that,
 * uses absolute positions to align nodes on top of the european map.
 */
public class CitiesToGraphviz {
    private static final int MAP_WIDTH = 1920;
    private static final int MAP_HEIGHT = 1080;

    private static final Map<String, NodeInfo> NODE_INFO = Map.ofEntries(
            Map.entry("Amsterdam",   new NodeInfo(390, 300, 0.40)),
            Map.entry("Berlin",      new NodeInfo(540, 310, 0.4)),
            Map.entry("Bruksela",    new NodeInfo(350, 360, 0.35)),
            Map.entry("Kopenhaga",   new NodeInfo(520, 200, 0.55)),
            Map.entry("Luksemburg",  new NodeInfo(430, 390, 0.50)),
            Map.entry("Paryż",       new NodeInfo(340, 420, 0.35)),
            Map.entry("Belgrad",     new NodeInfo(670, 540, 0.40)),
            Map.entry("Budapeszt",   new NodeInfo(670, 430, 0.35)),
            Map.entry("Bukareszt",   new NodeInfo(780, 550, 0.45)),
            Map.entry("Kiszyniów",   new NodeInfo(820, 440, 0.40)),
            Map.entry("Zagrzeb",     new NodeInfo(590, 520, 0.4)),
            Map.entry("Praga",       new NodeInfo(560, 380, 0.35)),
            Map.entry("Warszawa",    new NodeInfo(680, 320, 0.5)),
            Map.entry("Berno",       new NodeInfo(440, 480, 0.35)),
            Map.entry("Lublana",     new NodeInfo(550, 480, 0.40)),
            Map.entry("Bratysława",  new NodeInfo(620, 460, 0.40)),
            Map.entry("Wiedeń",      new NodeInfo(580, 420, 0.30)),
            Map.entry("Kijów",       new NodeInfo(860, 370, 0.35)),
            Map.entry("Mińsk",       new NodeInfo(820, 280, 0.35)),
            Map.entry("Wilno",       new NodeInfo(770, 260, 0.35))
    );


    private static String toGraphvizPos(int xPx, int yPx) {
        double xPt = xPx / 1.3333;
        double yPt = (MAP_HEIGHT - yPx) / 1.3333;
        return xPt + "," + yPt;
    }

    public static void convertToGraphviz(String inputFile, String outputFile) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        List<Edge> edges = new ArrayList<>();
        Set<String> cities = new HashSet<>();

        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            String cityA = parts[0];
            String cityB = parts[2];
            int weight = Integer.parseInt(parts[3]);

            edges.add(new Edge(cityA, cityB, weight));
            cities.add(cityA);
            cities.add(cityB);
        }

        br.close();

        out.println("graph Cities {");
        out.printf("    size=\"%.3f,%.3f!\";%n", MAP_WIDTH / 96.0, MAP_HEIGHT / 96.0);
        out.println("    ratio=fixed;");
        out.println("    layout=neato;");
        out.println("    overlap=false;");
        out.println("    splines=true;");
        out.println("    node [shape=circle, fontsize=12];");
        out.println("    graph [bgcolor=\"transparent\"];");
        out.println();

        for (String city : cities) {
            NodeInfo info = NODE_INFO.get(city);
            if (info == null) continue;

            String pos = toGraphvizPos(info.xPx, info.yPx);

            out.printf(
                    "    \"%s\" [pos=\"%s!\", width=%.3f, height=%.3f, fixedsize=true, fontsize=%d];%n",
                    city, pos, info.sizeInches, info.sizeInches, 6
            );
        }


        out.println();

        for (Edge e : edges) {
            out.printf("    \"%s\" -- \"%s\" [label=%d fontsize=%d fontcolor=\"red\", fontname=\"Helvetica Bold\"];%n",
                    e.cityA, e.cityB, e.weight, 8);
        }

        out.println("}");
        out.close();
    }

    public static void main(String[] args) throws Exception {
        String inputFile = "stolice_mst.txt";
        String outputFile = "stolice_mst.dot";

        convertToGraphviz(inputFile, outputFile);

        ProcessBuilder pb = new ProcessBuilder(
                "neato",
                "-n2",
                "-Tpng",
                "-Gdpi=96",
                outputFile,
                "-o",
                "stolice_mst.png"
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

    static class NodeInfo {
        int xPx;
        int yPx;
        double sizeInches;

        NodeInfo(int xPx, int yPx, double sizeInches) {
            this.xPx = xPx;
            this.yPx = yPx;
            this.sizeInches = sizeInches;
        }
    }

}
