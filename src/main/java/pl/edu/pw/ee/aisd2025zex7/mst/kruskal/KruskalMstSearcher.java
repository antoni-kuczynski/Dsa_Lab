package pl.edu.pw.ee.aisd2025zex7.mst.kruskal;

import pl.edu.pw.ee.aisd2025zex7.mst.MstSearchResult;
import pl.edu.pw.ee.aisd2025zex7.mst.MstSearcher;

import java.util.*;

public class KruskalMstSearcher extends MstSearcher {

    @Override
    public MstSearchResult findMst(int[][] edges, Map<Integer, String> nodeToCity) {

        if (edges == null || edges.length == 0) {
            throw new IllegalArgumentException("Brak krawędzi");
        }

        // 1️⃣ liczba wierzchołków (jak w GfG: V)
        int V = 0;
        for (int[] e : edges) {
            V = Math.max(V, Math.max(e[0], e[1]));
        }
        V++;

        // 2️⃣ sortowanie krawędzi po wadze (1:1 z GfG)
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));

        // 3️⃣ DSU
        DSU dsu = new DSU(V);
        int cost = 0;
        int count = 0;

        List<int[]> mstEdges = new ArrayList<>();

        // 4️⃣ Kruskal
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int w = e[2];

            if (dsu.find(x) != dsu.find(y)) {
                dsu.union(x, y);
                cost += w;
                mstEdges.add(e);

                if (++count == V - 1) {
                    break;
                }
            }
        }

        // 5️⃣ MST → int[]
        int[] connections = new int[mstEdges.size() * 3];
        int i = 0;
        for (int[] e : mstEdges) {
            connections[i++] = e[0];
            connections[i++] = e[1];
            connections[i++] = e[2];
        }

        return new MstSearchResult(connections, cost, nodeToCity);
    }

    // =========================
    // DSU – dokładnie jak w GfG
    // =========================
    private static class DSU {
        private final int[] parent;
        private final int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int x, int y) {
            int s1 = find(x);
            int s2 = find(y);

            if (s1 != s2) {
                if (rank[s1] < rank[s2]) {
                    parent[s1] = s2;
                } else if (rank[s1] > rank[s2]) {
                    parent[s2] = s1;
                } else {
                    parent[s2] = s1;
                    rank[s1]++;
                }
            }
        }
    }
}
