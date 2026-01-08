package pl.edu.pw.ee.aisd2025zex7.data.outcome;

public abstract class GraphSearchResult {

    private final int startId;
    private final int[] prevVertices;

    public GraphSearchResult(int startId, int[] prevVertices) {
        this.startId = startId;
        this.prevVertices = prevVertices;
    }

    public int getStartId() {
        return startId;
    }

    public int[] getPrevVertices() {
        return prevVertices;
    }

}
