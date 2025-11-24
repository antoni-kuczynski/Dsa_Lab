package pl.edu.pw.ee.aisd2025zex3.performance.grouping;

import pl.edu.pw.ee.aisd2025zex3.HashDoubleHashing;

public class HashDoubleHashingMitWordsGroupingVisualization extends MitWordsGroupingVisualization {
    
    private final static int INITIAL_SIZE = 133337; // prime: 133337 -> loadFactor = 0.75

    public HashDoubleHashingMitWordsGroupingVisualization() {
        super(HashDoubleHashing.class, INITIAL_SIZE);
    }

}
