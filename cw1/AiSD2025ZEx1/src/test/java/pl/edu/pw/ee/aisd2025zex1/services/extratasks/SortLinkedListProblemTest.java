package pl.edu.pw.ee.aisd2025zex1.services.extratasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SortLinkedListProblemTest {
    private SortLinkedListProblem sorter;

    private ListItem createLinkedListFromArray(int[] arr) {
        ListItem result = new ListItem(0);
        ListItem tempPtr = result;
        for (int i = 0; i < arr.length; i++) {
            result.setNext(
                    new ListItem(arr[i])
            );
            result = result.getNext();
        }
        return tempPtr.getNext();
    }

    private boolean compareTwoLinkedLists(ListItem l1, ListItem l2) {
        while (l1 != null && l2 != null) {
            if (l1.getValue() != l2.getValue())
                return false;

            l1 = l1.getNext();
            l2 = l2.getNext();
        }

        if (l1 == null && l2 == null)
            return true;

        return false;
    }

    @BeforeEach
    public void init() {
        sorter = new SortLinkedListProblem();
    }

    @Test
    public void shouldSort_ForRandomSmallData() {
        //given
        int[] data = {1,5,67,3,765,123,4,21,10};
        ListItem head = createLinkedListFromArray(data);

        //when
        int[] dataSorted = new int[data.length];
        System.arraycopy(data, 0, dataSorted, 0, data.length);
        ListItem sortedHead = createLinkedListFromArray(dataSorted);
        Arrays.sort(dataSorted);
        head = sorter.sort(head);

        //then
        boolean b = compareTwoLinkedLists(head, sortedHead);
        if (!b) {
            System.err.println("Expected: " + Arrays.toString(dataSorted) + "\nActual: " + head.toString());
            fail();
        }
    }

}