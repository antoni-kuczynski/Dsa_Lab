package pl.edu.pw.ee.aisd2025zex3.performance.grouping;

import pl.edu.pw.ee.aisd2025zex3.HashLinearProbing;

public class HashLinearProbingRandWordsGroupingVisualization extends RandWordsGroupingVisualization {

    private final static int INITIAL_SIZE = 133337; // prime: 133337 -> loadFactor = 0.75

    public HashLinearProbingRandWordsGroupingVisualization() {
        super(HashLinearProbing.class, INITIAL_SIZE);
    }

}
