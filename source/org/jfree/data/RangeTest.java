package org.jfree.data;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jfree.data.DataUtilities;
import org.jfree.data.Range; 
import org.junit.*; 
 
public class RangeTest { 
 
 private Range rangeObjectUnderTest;

 
 
 
 @Before 
 public void setUp(){ 
	 rangeObjectUnderTest = new Range(1,1);
     
 
 }
 
 @After 
 public void tearDown(){ 
	 rangeObjectUnderTest = null;
 } 
 
 
 //Shift test cases
 
 //Moves all values to right by 5 in rangeObjectUnderTest and 
 // allows zero crossing
 //passes
 @Test
 public void testShiftPositiveDeltaWithAllowZeroCrossingTrue() {
	 rangeObjectUnderTest = new Range(-1, 5);
	 Range test2 = new Range(4, 10);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 5,true);
	 assertEquals("The new shifted range should be as expected", 
			 test2, rangeObjectUnderTest);
	 
	   
 }
 
 @Test
 public void testShiftNegativeDeltaWithAllowZeroCrossingFalseAndValueEqualToZero() {
	 rangeObjectUnderTest = new Range(-10, 0);
	 Range test2 = new Range(-20, 0);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -10,false);
	 assertEquals("The new shifted range should be as expected", 
			 test2, rangeObjectUnderTest);
	 
	   
 }
 
 
//Moves all values to left by -5 in rangeObjectUnderTest and 
// allows zero crossing
//passes
 @Test
 public void testShiftNegativeDeltaWithAllowZeroCrossingTrue() {
	 rangeObjectUnderTest = new Range(1, 5);
	 Range test2 = new Range(-4, 0);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -5,true);
	 assertEquals("The new shifted range should be as expected",  
			 test2, rangeObjectUnderTest);
	 
	   
 }
 
 
//Moves all values move by 0 in rangeObjectUnderTest and 
//allows zero crossing
//passes
 @Test
 public void testShiftByZero() {
	 rangeObjectUnderTest = new Range(1, 5);
	 Range test2 = new Range(1, 5);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 0,true);
	 assertEquals("The new shifted range should be as expected",  
			 test2, rangeObjectUnderTest);
	 
	   
 }
 
 
 
//Moves all values to right by 5 in rangeObjectUnderTest and 
//doesn't allow zero crossing
//fails
 @Test
 public void testShiftPositiveDeltaWithAllowZeroCrossingFalse() {
	 rangeObjectUnderTest = new Range(-1, 5);
	 Range test2 = new Range(0, 10);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 5,false);
	 assertEquals("The new shifted range should be as expected",  
			 test2, rangeObjectUnderTest);
	 
	   
 }
 
//Moves all values to left by -5 in rangeObjectUnderTest and 
// doesn't allows zero crossing
//passes
 @Test
 public void testShiftNegativeDeltaWithAllowZeroCrossingFalse() {
	 rangeObjectUnderTest = new Range(-1, 5);
	 Range test2 = new Range(-6, 0);
	 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -5,false);
	 assertEquals("The new shifted range should be as expected",  
			 test2, rangeObjectUnderTest);
	 
	   
 }
//test to see if shift() allows null values
//passes
 @Test
 public void testShiftWhenNullValueIsEntered() throws Exception {
	 rangeObjectUnderTest = null;
	 try {
		 rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -5,false);
	        fail("Expected NullPointerException was not thrown");
	    } catch (NullPointerException e) {
	        // expected exception was thrown, test passed
	    }
	}
	 
	    
	 
	 
 
 
 
// intersects test cases
 
 
//test to see if intersects returns false if 
//input lower and upper are outside and greater than range values
//passes
 @Test 
 public void testIntersectsInputLowerAndUpperBoundWhenGreaterThanRangeUpperBound() {
	 rangeObjectUnderTest = new Range(5, 6);
  assertFalse( rangeObjectUnderTest.intersects(7,8)); 
 
}
 
 @Test 
 public void test2IntersectsInputLowerAndUpperBoundWhenGreaterThanRangeUpperBound() {
	 rangeObjectUnderTest = new Range(0, 10);
  assertFalse( rangeObjectUnderTest.intersects(5,15)); 
 
}
 
 @Test 
 public void testIntersectsInputValuesAreSubsetOfRange() { 
	 rangeObjectUnderTest = new Range(6, 12);
  assertTrue(rangeObjectUnderTest.intersects(10,11)); 
 
}
 
 @Test
 public void testIntersectsInputsLowerAndUpperBoundWhenLessThanRangeLowerbound() { 
	 rangeObjectUnderTest = new Range(7,8);
     assertFalse(rangeObjectUnderTest.intersects(5,6)); 
 
}
 
 @Test
 public void testIntersectsInputsWhenNegativeAndRangeIsPositive() { 
	 rangeObjectUnderTest = new Range(1,5);
     assertFalse(rangeObjectUnderTest.intersects(-5,-1)); 
 
}
 @Test
 public void testIntersectsInputIsSubsetOfRangeWhenBothAreNegative() { 
	 rangeObjectUnderTest = new Range(-10,-5);
     assertTrue(rangeObjectUnderTest.intersects(-8,-5)); 
 
}
 
 @Test
 public void testIntersectInputIsSubsetOfRangeWithDecimalPoint() { 
	 rangeObjectUnderTest = new Range(0,1);
     assertTrue(rangeObjectUnderTest.intersects(.25,.5)); 
 
}
 
 @Test
 public void testIntersectInputIsSubsetOfRangeWithNegativeDecimalPoint() { 
	 rangeObjectUnderTest = new Range(-2.50,-1.5);
     assertTrue(rangeObjectUnderTest.intersects(-1.75,-0.5)); 
 
}
 
 @Test
 public void testIntersectInputIsSubsetOfRangeWithNegativeDecimalPoint2() { 
	 rangeObjectUnderTest = new Range(2.50,15);
     assertTrue(rangeObjectUnderTest.intersects(-1.75,15)); 
 
}

 @Test
 public void testIntersectInputIsSubsetOfRangeWithNegativeDecimalPoint3() { 
	 rangeObjectUnderTest = new Range(-1.85,15);
     assertTrue(rangeObjectUnderTest.intersects(-1.75, -1.75)); 
 
}
  
 
 
 //Lower Bound Test cases
 
 
 @Test 
 public void testLowerBoundValueBothPositiveAndEqual() { 
	 rangeObjectUnderTest = new Range(5, 5);
  assertEquals("The lower bound value of the range should be 5", 
     5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 } 
 
 @Test 
 public void testLowerBoundValueBothPositiveAndNotEqual() { 
	 rangeObjectUnderTest = new Range(5, 10);
  assertEquals("The lower bound value of the range should be 5", 
     5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundValueBothNegativeAndEqual() { 
	 rangeObjectUnderTest = new Range(-5, -5);
  assertEquals("The lower bound value of the range should be -5", 
     -5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundValueBothNegativeAndNotEqual() { 
	 rangeObjectUnderTest = new Range(-5, -2);
  assertEquals("The lower bound value of the range should be -5", 
     -5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundValuesBothZero() { 
	 rangeObjectUnderTest = new Range(0, 0);
  assertEquals("The lower bound value of the range should be 0", 
     0, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundValueZero() { 
	 rangeObjectUnderTest = new Range(0, 10);
  assertEquals("The lower bound value of the range should be 0", 
     0, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundOneNegativeAndOnePositiveValue() { 
	 rangeObjectUnderTest = new Range(-6, 10);
  assertEquals("The lower bound value of the range should be -6", 
     -6, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundBothValuesPositiveDecimalPoint() { 
	 rangeObjectUnderTest = new Range(2.5, 9.5);
	 assertEquals("The lower bound value of the range should be 2.5", 
     2.5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 @Test 
 public void testLowerBoundBothValuesNegativeDecimalPoint() { 
	 rangeObjectUnderTest = new Range(-5.5, -2.5);
	 assertEquals("The lower bound value of the range should be -5.5", 
     -5.5, rangeObjectUnderTest.getLowerBound(), 0.000000001d); 
 }
 
 
 // ToString Test Cases
 
 
 //Test case below fails but should pass 
 @Test 
 public void testToStringValuePositiveAndNegative() { 
	 rangeObjectUnderTest = new Range(-5.0, 5.0);
     assertEquals("The String representation of this range should be: Range[-5.0,5.0]"
    		 ,"Range[-5.0,5.0]", rangeObjectUnderTest.toString()); }
 
//Test case below fails but should pass 
 @Test 
 public void testToStringValueNegativeAndPositiveDecimalPoint() { 
	 rangeObjectUnderTest = new Range(-1.5, 1.5);
     assertEquals("The String representation of this range should be: Range[-1.5,1.5]"
    		 ,"Range[-1.5,1.5]", rangeObjectUnderTest.toString()); 
 }
 
//Test case below fails but should pass 
 @Test 
 public void testToStringValuesBothPositiveAndNotEqual() { 
	 rangeObjectUnderTest = new Range(6.0, 10.0);
     assertEquals("The String representation of this range should be: Range[6.0,10.0]",
    		 "Range[6.0,10.0]", rangeObjectUnderTest.toString());
 }
 

@Test 
public void testToStringValuesBothPositiveAndEqual() { 
	rangeObjectUnderTest = new Range(1.0, 1.0);
    assertEquals("The String representation of this range should be: Range[1.0,1.0]",
   		"Range[1.0,1.0]", rangeObjectUnderTest.toString());
}

@Test 
public void testToStringValuesBothNegativeAndEqual() { 
	rangeObjectUnderTest = new Range(-1.0, -1.0);
    assertEquals("The String representation of this range should be: Range[-1.0,-1.0]",
   		"Range[-1.0,-1.0]", rangeObjectUnderTest.toString());
}

//Test case below fails but should pass 
@Test 
public void testToStringValuesBothNegativeAndNotEqual() { 
	rangeObjectUnderTest = new Range(-10.0, -1.0);
    assertEquals("The String representation of this range should be: Range[-10.0,-1.0]",
   		"Range[-10.0,-1.0]", rangeObjectUnderTest.toString());
}

//Test case below fails but should pass 
@Test 
public void testToStringValuesBothPositiveDecimalPoints() { 
	rangeObjectUnderTest = new Range(10.6, 18.3);
	assertEquals("The String representation of this range should be: Range[10.6,18.3]",
 		"Range[10.6,18.3]", rangeObjectUnderTest.toString());
}

//Test case below fails but should pass 
@Test 
public void testToStringValuesBothNegativeDecimalPoints() { 
	rangeObjectUnderTest = new Range(-3.5, -1.5);
	assertEquals("The String representation of this range should be: Range[-3.5,-1.5]",
		"Range[-3.5,-1.5]", rangeObjectUnderTest.toString());
}

@Test 
public void testToStringValuesEqualAndZero() { 
	rangeObjectUnderTest = new Range(0.0, 0.0);
    assertEquals("The String representation of this range should be: Range[0.0,0.0]",
   		"Range[0.0,0.0]", rangeObjectUnderTest.toString());
}


// Constrain Test Cases


@Test 
public void testConstrainValueWithinRange() { 
	rangeObjectUnderTest = new Range(-5, 5);
    assertEquals("The value returned should be: 0.0",
   		0.0, rangeObjectUnderTest.constrain(0.0), 0.000000001d);
}

@Test 
public void testConstrainValueWithinRangeNegativeValue() { 
	rangeObjectUnderTest = new Range(-8, -5);
    assertEquals("The value returned should be: 0.0",
   		-6, rangeObjectUnderTest.constrain(-6), 0.000000001d);
}

//Test case below fails but should pass 
@Test 
public void testConstrainValueBelowRange() { 
    rangeObjectUnderTest = new Range(-5, 5);
    assertEquals(-5.0, rangeObjectUnderTest.constrain(-10.0), 0.0001);
}

//Test case below fails but should pass 
@Test 
public void testConstrainValueOutsideBelowRange() { 
  rangeObjectUnderTest = new Range(5, 10);
  assertEquals(5, rangeObjectUnderTest.constrain(2), 0.0001);
}
@Test 
public void testConstrainValueAboveRange() { 
    rangeObjectUnderTest = new Range(-2, 5);
    assertEquals(5.0, rangeObjectUnderTest.constrain(10.0), 0.0001);
}

@Test 
public void testConstrainValueEqualToLowerBound() { 
    rangeObjectUnderTest = new Range(-5, 5);
    assertEquals(-5.0, rangeObjectUnderTest.constrain(-5.0), 0.0001);
}

@Test 
public void testConstrainValueEqualToUpperBound() { 
    rangeObjectUnderTest = new Range(-5, 5);
    assertEquals(5.0, rangeObjectUnderTest.constrain(5.0), 0.0001);
}

@Test 
public void testConstrainValueWithinRangeCloserToLowerBound() { 
    rangeObjectUnderTest = new Range(-5, 5);
    assertEquals(-4.0, rangeObjectUnderTest.constrain(-4.0), 0.0001);
}

@Test 
public void testConstrainValueWithinRangeCloserToUpperBound() { 
    rangeObjectUnderTest = new Range(-5, 5);
    assertEquals(4.0, rangeObjectUnderTest.constrain(4.0), 0.0001);
}

@Test 
public void testConstrainValueWithinRangeEquidistantFromBothBounds() { 
    rangeObjectUnderTest = new Range(0, 5);
    assertEquals(2.5, rangeObjectUnderTest.constrain(2.5), 0.0001);
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

@Test
public void testCombineRange2IsNull() {
    Range range1 = new Range(0, 10);
    Range result = Range.combine(range1, null);
    Range expected = new Range(0, 10);
    assertEquals(expected, result);
}


 @Test 
 public void testCentralValueShouldBeZero() { 
	 rangeObjectUnderTest =new Range(-1,1);
  assertEquals("The central value of -1 and 1 should be 0", 
     0, rangeObjectUnderTest.getCentralValue(), 0.000000001d); 
 }
 
 

     @Test(expected = IllegalArgumentException.class)
     public void testExpandWithNullRange() {
         Range range = null;
         Range.expand(range, 0.1, 0.2);
     }

     @Test
     public void testExpandWithZeroMargins() {
         Range range = new Range(0.0, 1.0);
         Range expandedRange = Range.expand(range, 0.0, 0.0);
         assertEquals(0.0, expandedRange.getLowerBound(), 0.0001);
         assertEquals(1.0, expandedRange.getUpperBound(), 0.0001);
     }

     @Test
     public void testExpandWithPositiveMargins() {
         Range range = new Range(0.0, 1.0);
         Range expandedRange = Range.expand(range, 0.1, 0.2);
         assertEquals(-0.1, expandedRange.getLowerBound(), 0.0001);
         assertEquals(1.2, expandedRange.getUpperBound(), 0.0001);
     }

     @Test
     public void testExpandWithNegativeMargins() {
         Range range = new Range(0.0, 1.0);
         Range expandedRange = Range.expand(range, -0.1, -0.2);
         assertEquals(0.1, expandedRange.getLowerBound(), 0.0001);
         assertEquals(0.8, expandedRange.getUpperBound(), 0.0001);
     }
     
    

         @Test
         public void testExpandToIncludeWithNullRange() {
             Range range = null;
             Range expandedRange = Range.expandToInclude(range, 1.0);
             assertEquals(1.0, expandedRange.getLowerBound(), 0.0001);
             assertEquals(1.0, expandedRange.getUpperBound(), 0.0001);
         }

         @Test
         public void testExpandToIncludeWithValueBelowRange() {
             Range range = new Range(0.0, 1.0);
             Range expandedRange = Range.expandToInclude(range, -1.0);
             assertEquals(-1.0, expandedRange.getLowerBound(), 0.0001);
             assertEquals(1.0, expandedRange.getUpperBound(), 0.0001);
         }

         @Test
         public void testExpandToIncludeWithValueAboveRange() {
             Range range = new Range(0.0, 1.0);
             Range expandedRange = Range.expandToInclude(range, 2.0);
             assertEquals(0.0, expandedRange.getLowerBound(), 0.0001);
             assertEquals(2.0, expandedRange.getUpperBound(), 0.0001);
         }

         @Test
         public void testExpandToIncludeWithValueInRange() {
             Range range = new Range(0.0, 1.0);
             Range expandedRange = Range.expandToInclude(range, 0.5);
             assertEquals(0.0, expandedRange.getLowerBound(), 0.0001);
             assertEquals(1.0, expandedRange.getUpperBound(), 0.0001);
             assertSame(range, expandedRange);
         }
         @Test
         public void testConstructor() {
             // Test lower > upper
             try {
                 Range range = new Range(1.0, 0.0);
                 fail("Expected IllegalArgumentException");
             } catch (IllegalArgumentException e) {
                 assertEquals("Range(double, double): require lower (1.0) <= upper (0.0).", e.getMessage());
             }}
     }

 



