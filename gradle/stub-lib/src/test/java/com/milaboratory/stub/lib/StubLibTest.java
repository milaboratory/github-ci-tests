package com.milaboratory.stub.lib;
import org.junit.Test;

import static org.junit.Assert.*;

public class StubLibTest {
    @Test
    public void testStubSuccess() {
        assertEquals(10, 10);
    }

    @Test
    public void testStubFailure() {
        assertEquals(10, 8);
    }
}
