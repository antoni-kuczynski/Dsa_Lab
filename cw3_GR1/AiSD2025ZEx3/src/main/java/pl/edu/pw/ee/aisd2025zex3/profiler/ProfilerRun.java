package pl.edu.pw.ee.aisd2025zex3.profiler;

import pl.edu.pw.ee.aisd2025zex3.HashLinearProbing;
import static pl.edu.pw.ee.aisd2025zex3.dataUtils.WordsGenerator.prepareWordsFromFile;
import pl.edu.pw.ee.aisd2025zex3.services.HashTable;

public class ProfilerRun {

    public static void main(String[] args) {
        int size = 4095; //{4095, 8191, 16_383, 32_771, 65_927, 131_357, 263_293, 524_413};

        String[] words = prepareWordsFromFile(); // prepareRandomWords();

        HashTable<String> hash = new HashLinearProbing<>(size); // new HashDoubleHashing<>(size);

        putWordsIntoHash(hash, words);
    }

    private static void putWordsIntoHash(HashTable<String> hash, String[] words) {
        int n = words.length;

        for (int i = 0; i < n; i++) {
            hash.put(words[i]);
        }
    }
}
