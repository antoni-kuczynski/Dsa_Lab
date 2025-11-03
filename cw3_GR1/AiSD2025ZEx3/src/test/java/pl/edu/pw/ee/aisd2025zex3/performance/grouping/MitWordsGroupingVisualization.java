package pl.edu.pw.ee.aisd2025zex3.performance.grouping;

import pl.edu.pw.ee.aisd2025zex3.HashOpenAddressing;
import static pl.edu.pw.ee.aisd2025zex3.dataUtils.WordsGenerator.prepareWordsFromFile;

public abstract class MitWordsGroupingVisualization extends GroupingVisualization {

    public MitWordsGroupingVisualization(Class<? extends HashOpenAddressing> hashClass, int initialSize) {
        super(hashClass, initialSize);
    }

    @Override
    String[] prepareWords() {
        return prepareWordsFromFile();
    }

}
