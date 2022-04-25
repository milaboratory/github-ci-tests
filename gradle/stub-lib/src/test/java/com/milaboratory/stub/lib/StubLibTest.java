package com.milaboratory.stub.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubLibTest {
    @Test
    public void testStubSuccess() {
        assertEquals(10, 10);
    }

    @Test
    public void testStubFailure() {
        assertEquals(10, 8);
    }

    @Test
    public void testExit1() {
        System.exit(1);
    }
}
