package pl.edu.pw.ee.aisd2025zex7.graphsearch.services;

import pl.edu.pw.ee.aisd2025zex7.data.input.Graph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.GraphSearchResult;

public interface GraphSearcher {

    GraphSearchResult searchGraphPaths(Graph graph, int startId);
}
