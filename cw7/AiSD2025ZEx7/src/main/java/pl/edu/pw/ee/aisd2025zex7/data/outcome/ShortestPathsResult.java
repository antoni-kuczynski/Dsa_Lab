package pl.edu.pw.ee.aisd2025zex7.data.outcome;

import java.util.Arrays;

public class ShortestPathsResult {

    private final int srcId;
    private final int destId;
    private final int minDistance;
    private final int[] distances;

    public ShortestPathsResult(int srcId, int destId, int minDistance, int[] distances) {
        this.srcId = srcId;
        this.destId = destId;
        this.minDistance = minDistance;
        this.distances = distances;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public int[] getDistances() {
        return distances;
    }

    @Override
    public String toString() {
        return "ShortestPathsResult{"
                + "srcId=" + srcId
                + ", destId=" + destId
                + ", minDistance=" + minDistance
                + ", distances=" + Arrays.toString(distances)
                + '}';
    }

}
