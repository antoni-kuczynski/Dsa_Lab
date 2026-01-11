package pl.edu.pw.ee.aisd2025zex7.mst;

import java.util.Map;

public class MstSearchResult {

    private int[] connections;
    private int minDistance;
    private Map<Integer, String> nodeToCity;

    public MstSearchResult(int[] connections, int minDistance,
                           Map<Integer, String> nodeToCity) {
        this.connections = connections;
        this.minDistance = minDistance;
        this.nodeToCity = nodeToCity;
    }

    public int[] getConnections() {
        return connections;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public Map<Integer, String> getNodeToCity() {
        return nodeToCity;
    }

    public void print() {
        for (int i = 0; i < connections.length; i += 3) {
            int u = connections[i];
            int v = connections[i + 1];
            int w = connections[i + 2];

            String cityU = nodeToCity.getOrDefault(u, String.valueOf(u));
            String cityV = nodeToCity.getOrDefault(v, String.valueOf(v));

            System.out.println(cityU + " <-> " + cityV + " " + w);
        }

        System.out.println("\nŁączny dystans MST: " + minDistance);
    }
}
