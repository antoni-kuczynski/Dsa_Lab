package pl.edu.pw.ee.aisd2025zex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class HashOpenAddressingTest {
    private HashOpenAddressing hash;

    public HashOpenAddressingTest(HashOpenAddressing hash) {
        this.hash = hash;
    }

    @Test
    void shouldPutAndGetElement() {
        //given
        hash.put(42);

        //then
        assertEquals(42, hash.get(42));
    }

    @Test
    void shouldReturnNullWhenElementNotPresent() {
        //given
        hash.put(124132);

        //then
        assertNull(hash.get(99));
    }

    @Test
    void shouldDeleteElement() {
        //given
        hash.put(10);

        //when
        hash.delete(10);

        //then
        assertNull(hash.get(10));
    }

    @Test
    void shouldReuseDeletedSlot() {
        //given
        hash.put(1);

        //when
        hash.delete(1);
        hash.put(12);

        //then
        assertEquals(12, hash.get(12));
    }

    @Test
    void shouldThrowExceptionWhenPuttingNull() {
        assertThrows(IllegalArgumentException.class, () -> hash.put(null));
    }

    @Test
    void shouldThrowExceptionWhenGettingNull() {
        assertThrows(IllegalArgumentException.class, () -> hash.get(null));
    }

    @Test
    void shouldThrowExceptionWhenDeletingNull() {
        assertThrows(IllegalArgumentException.class, () -> hash.delete(null));
    }

    @Test
    void shouldCountHashCalls() {
        hash.put(5);
        hash.put(16);
        assertTrue(hash.getAmountOfHashCalls() >= 2);
    }

}