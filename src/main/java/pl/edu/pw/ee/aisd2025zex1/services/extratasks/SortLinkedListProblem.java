package pl.edu.pw.ee.aisd2025zex1.services.extratasks;

/*
 * Dana jest list (początek listy).
 * Zwróć uporządkowaną listę w porządku rosnącym
 */
public class SortLinkedListProblem {

    public ListItem sort(ListItem head) {
        validateData(head);

        return mergeSort(head);
    }

    public ListItem mergeSort(ListItem head) {
        if (head == null || head.getNext() == null)
            return head;

        ListItem middle = findMiddleOfList(head);

        ListItem left = head;
        ListItem right = middle.getNext();
        middle.setNext(null);

        left = mergeSort(left);
        right = mergeSort(right);

        return getMergedLists(left, right);
    }

    public ListItem getMergedLists(ListItem left, ListItem right) {
        ListItem newHead = new ListItem(0);
        ListItem tempStart = newHead;

        while (left != null && right != null) {
            if (left.getValue() <= right.getValue()) {
                newHead.setNext(left);
                left = left.getNext();
            } else {
                newHead.setNext(right);
                right = right.getNext();
            }
            newHead = newHead.getNext();
        }

        while (left != null) {
            newHead.setNext(left);
            newHead = newHead.getNext();
            left = left.getNext();
        }

        while (right != null) {
            newHead.setNext(right);
            newHead = newHead.getNext();
            right = right.getNext();
        }
        return tempStart.getNext();
    }

    private ListItem findMiddleOfList(ListItem head) {
        if (head == null || head.getNext() == null)
            return head;

        ListItem slowPointer = head;
        ListItem fastPointer = head.getNext();

        while (fastPointer != null && fastPointer.getNext() != null) {
            slowPointer = slowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();
        }
        return slowPointer;
    }

    private void validateData(ListItem head) {
        if (head == null) {
            throw new IllegalArgumentException("List cannot be null!");
        }
    }
}
