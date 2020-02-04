package com.codeup.deimosspringblog.services;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SillySvcTest {

    private SillySvc sillySvc;

    @Before
    public void setUp() {
        this.sillySvc = new SillySvc();
    }

    @Test
    public void testServiceNotNull() {
        assertNotNull(sillySvc);
    }

    @Test
    public void testTwoIsTwo() {
        assertEquals(2, 2);
    }

    @Test
    public void testSayHi() {
        assertEquals("Hi!", sillySvc.sayHi());
    }



}
