package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeMutationTest extends TestCase {

	

	@Before
	protected void setUp() throws Exception {
		Range range1 = new Range(0, 10);
	    Range range2 = new Range(5, 15);
	}

	@After
	protected void tearDown() throws Exception {
		Range range1 = null;
	    Range range2 = null;
	}

	@Test
	public void testCombineRange1IsNull() {
	    Range range2 = new Range(0, 10);
	    Range expected = new Range(0, 10);
	    assertEquals(expected, Range.combine(null, range2));
	}

	@Test
	public void testCombineRangesDoNotOverlap() {
	    Range range1 = new Range(0, 10);
	    Range range2 = new Range(20, 30);
	    Range expected = new Range(0, 30);
	    assertEquals(expected, Range.combine(range1, range2));
	}

	@Test
	public void testCombineRangesPartiallyOverlap() {
	    Range range1 = new Range(0, 10);
	    Range range2 = new Range(5, 15);
	    Range result = Range.combine(range1, range2);
	    Range expected = new Range(0, 15);
	    assertEquals(expected, result);
	}

	@Test
	public void testCombineRangesAreTheSame() {
	    Range range1 = new Range(0, 10);
	    Range range2 = new Range(0, 10);
	    Range result = Range.combine(range1, range2);
	    Range expected = new Range(0, 10);
	    assertEquals(expected, result);
	}

	@Test
	public void testCombineBothRangesAreNull() {
	    Range result = Range.combine(null, null);
	    assertNull(null, result);
	}

	/*
	 * @Test public void testCombineRange2IsNull() { Range range1 = new Range(0,
	 * 10); Range result = Range.combine(range1, null); Range expected = new
	 * Range(0, 10); assertEquals(expected, result); }
	 */
	
	
}
