package pl.edu.pw.ee.aisd2025zex7.data.input;

public interface WeightedGraph extends Graph {

    int getWeight(int srcId, int destId);
}
