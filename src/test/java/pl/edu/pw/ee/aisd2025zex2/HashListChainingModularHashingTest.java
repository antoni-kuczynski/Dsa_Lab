package pl.edu.pw.ee.aisd2025zex2;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex2.services.HashTable;
import pl.edu.pw.ee.aisd2025zex2.utils.GeneralHashListChainingTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2025zex2.utils.AdvancedGetters.getHashElemById;
import static pl.edu.pw.ee.aisd2025zex2.utils.AdvancedGetters.getNumOfElems;


public class HashListChainingModularHashingTest extends GeneralHashListChainingTest {

    public HashListChainingModularHashingTest() {
        super(HashListChainingModularHashing.class);
    }

    @Test
    public void should_CorrectlyAddThreeDifferentElems_WhenHashSizeIsOne() {
        // given
        int hashSize = 1;
        hashString = new HashListChainingModularHashing<>(hashSize);
        hashString.add("Ola");
        hashString.add("Ala");
        hashString.add("Ula");

        // when
        int nOfElemsInHash = getNumOfElems(hashString);
        String firstName = getHashElemById(hashString, 0);
        String secondName = getHashElemById(hashString, 1);
        String thirdName = getHashElemById(hashString, 2);

        // then
        assertThat(nOfElemsInHash).isEqualTo(3);
        assertThat(firstName).isEqualTo("Ula");
        assertThat(secondName).isEqualTo("Ala");
        assertThat(thirdName).isEqualTo("Ola");
    }

}