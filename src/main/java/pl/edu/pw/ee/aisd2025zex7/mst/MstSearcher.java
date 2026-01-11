package pl.edu.pw.ee.aisd2025zex7.mst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MstSearcher {

    public MstSearchResult getMstResult(File weightedList) {
        List<int[]> edges = new ArrayList<>();
        Map<Integer, String> nodeToCity = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(weightedList))) {

            Map<String, Integer> index = new HashMap<>();

            String line;
            int counter = 0;


            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                // Format: "Amsterdam <-> Berlin 684"
                String[] p = line.split(" ");
                String a = p[0];
                String b = p[2];
                int w = Integer.parseInt(p[3]);

                // nadajemy numery miastom
                if (!index.containsKey(a)) index.put(a, counter++);
                if (!index.containsKey(b)) index.put(b, counter++);

                int u = index.get(a);
                int v = index.get(b);

                edges.add(new int[]{u, v, w});
            }


            for (Map.Entry<String, Integer> e : index.entrySet()) {
                nodeToCity.put(e.getValue(), e.getKey());
            }


            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // konwersja List<int[]> → int[][]
        return findMst(edges.toArray(new int[0][]), nodeToCity);
    }

    public abstract MstSearchResult findMst(int[][] edges, Map<Integer, String> nodeToCity);

}