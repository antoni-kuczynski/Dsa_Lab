package pl.edu.pw.ee.aisd2025zex2.stats;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex2.HashListChainingModularHashing;
import pl.edu.pw.ee.aisd2025zex2.services.HashTable;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2025zex2.utils.AdvancedGetters.getHashElemById;
import static pl.edu.pw.ee.aisd2025zex2.utils.AdvancedGetters.getNumOfElems;

public class ChainLengthStatsModularHashingTest extends ChainLengthStatsTest {

    public ChainLengthStatsModularHashingTest() {
        super(HashListChainingModularHashing.class);
    }


}
