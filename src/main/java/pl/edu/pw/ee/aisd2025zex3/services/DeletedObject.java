package pl.edu.pw.ee.aisd2025zex3.services;

public class DeletedObject implements Comparable {
    public static DeletedObject DELETED = new DeletedObject();

    @Override
    public int compareTo(Object o) {
        return 1;
    }

    private DeletedObject() {

    }
}
