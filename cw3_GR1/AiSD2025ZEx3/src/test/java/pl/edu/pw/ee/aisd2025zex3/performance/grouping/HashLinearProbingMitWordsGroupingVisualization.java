package pl.edu.pw.ee.aisd2025zex3.performance.grouping;

import pl.edu.pw.ee.aisd2025zex3.HashLinearProbing;

public class HashLinearProbingMitWordsGroupingVisualization extends MitWordsGroupingVisualization {

    private final static int INITIAL_SIZE = 133337; // prime: 133337 -> loadFactor = 0.75

    public HashLinearProbingMitWordsGroupingVisualization() {
        super(HashLinearProbing.class, INITIAL_SIZE);
    }

}
