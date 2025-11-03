package pl.edu.pw.ee.aisd2025zex3.performance.grouping;

import pl.edu.pw.ee.aisd2025zex3.HashOpenAddressing;
import static pl.edu.pw.ee.aisd2025zex3.dataUtils.WordsGenerator.prepareRandomWords;

public abstract class RandWordsGroupingVisualization extends GroupingVisualization {

    public RandWordsGroupingVisualization(Class<? extends HashOpenAddressing> hashClass, int initialSize) {
        super(hashClass, initialSize);
    }

    @Override
    String[] prepareWords() {
        return prepareRandomWords();
    }

}
