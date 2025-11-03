package pl.edu.pw.ee.aisd2025zex3.performance.time;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex3.HashOpenAddressing;
import pl.edu.pw.ee.aisd2025zex3.services.HashTable;
import static pl.edu.pw.ee.aisd2025zex3.utils.AdvancedConstructors.createHashInstance;

public abstract class WordsPerformanceTest {

    private final Class<? extends HashOpenAddressing> hashClass;

    public WordsPerformanceTest(Class<? extends HashOpenAddressing> hashClass) {
        this.hashClass = hashClass;
    }

    @Test
    public void measurePerfomanceOfHashes() {
        int nOfRepeats = 30;
//        int[] initialSizes = {4095, 8191, 16_383, 32_771, 65_927, 131_357, 263_293, 524_413};
        int[] initialSizes = {5101, 8297, 16_493, 32_909, 65_651, 131_111, 263_023, 524_309}; //dodano wielkości podane w zadaniu
        String[] words = prepareWords();

        if (nOfRepeats <= 0) {
            return;
        }

        HashTable<String> hash = null;
        List<Long> times;
        long startTime, measuredTime;

        for (int size : initialSizes) {
            times = new ArrayList<>();

            for (int i = 0; i < nOfRepeats; i++) {
                hash = createHash(size);
                startTime = System.nanoTime();

                putWordsIntoHash(hash, words);

                measuredTime = System.nanoTime() - startTime;
                times.add(measuredTime);
            }

            countAndPrintMinAvgTime(times, size, (HashOpenAddressing) hash);
        }
    }

    abstract String[] prepareWords();

    private HashTable<String> createHash(int size) {
        return createHashInstance(size, hashClass);
    }

    private void putWordsIntoHash(HashTable<String> hash, String[] words) {
        int n = words.length;

        for (int i = 0; i < n; i++) {
            hash.put(words[i]);
        }
    }

    private void countAndPrintMinAvgTime(List<Long> timesAsList, int initSize, HashOpenAddressing hash) {
        int n = timesAsList.size();
        int startId = 10;
        int endId = 20;

        long sum = 0;
        long avgTime;

        timesAsList.sort(null);

        for (int i = startId; i < endId && i < n; i++) {
            sum += timesAsList.get(i);
        }

        avgTime = sum / (endId - startId);

        System.out.println(String.format(
                "Init size: %10d\t|\tTime: %15d\t|\tFinal_alpha: %10.15f\t|" +
                        "\tPut_count: %10d\t|\tHash_count: %10d\t|\tDouble_resize_count: %10d",
                initSize,
                avgTime,
                hash.countLoadFactor(),
                hash.getAmountOfPutCalls(),
                hash.getAmountOfHashCalls(),
                hash.getAmountOfDoubleResizeCalls()
        ));

    }
}
