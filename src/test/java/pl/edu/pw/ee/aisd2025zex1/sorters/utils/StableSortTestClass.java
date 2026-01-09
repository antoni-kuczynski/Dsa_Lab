package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import java.util.Objects;

public class StableSortTestClass implements Comparable<StableSortTestClass> {
    private final String s;
    private final int a;

    public StableSortTestClass(String s, int a) {
        this.s = s;
        this.a = a;
    }

    @Override
    public int compareTo(StableSortTestClass o) {
        return Integer.compare(this.a, o.a);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StableSortTestClass stableSortTestClass = (StableSortTestClass) o;
        return a == stableSortTestClass.a && Objects.equals(s, stableSortTestClass.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, a);
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "s='" + s + '\'' +
                ", a=" + a +
                '}';
    }
}
