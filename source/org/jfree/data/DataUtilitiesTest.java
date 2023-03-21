package org.jfree.data;



import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Range;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest {
	private Values2D values2D;

	@Before
	public void setUp() {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(3, 1, 0);
		testValues.addValue(3, 2, 0);
		testValues.addValue(2, 0, 1);
		testValues.addValue(2, 1, 1);
		testValues.addValue(4, 2, 1);
		testValues.addValue(3, 0, 2);
		testValues.addValue(3, 1, 2);
		testValues.addValue(3, 2, 2);

	}

	@After
	public void tearDown() {
		values2D = null;
	}

	// Test case failed due to invalid column index- should return 0 for invalid
	 // input
	 @Test
	 public void testValidDataAndInValidNegativeColumnIndex() {
	 try {
	 assertEquals("Sum returned should be 0", 0, DataUtilities.calculateColumnTotal(values2D, -1), 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 fail("An unexpected IndexOutOfBoundsException was thrown");
	 }
	 }
	
	//TC failed - method does not return an exception
	 @Test
	 public void testNullDataandColumnIndex() {
	 try {
	 DataUtilities.calculateColumnTotal(null, 1);
	 fail("No exception thrown. The expected outcome was: a thrown "
	 + "exception of type: IllegalArgumentException");
	 } catch (Exception e) {
	 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
	 }
	 }
	 
	//TC failed - method does not return an exception
	 @Test
	 public void testNullDataandNegativeColumnIndex() {
	 try {
	 DataUtilities.calculateColumnTotal(null, -3);
	 fail("No exception thrown. The expected outcome was: a thrown "
	 + "exception of type: IllegalArgumentException");
	 } catch (Exception e) {
	 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
	 }
	 }
	 
	//TC failed - method does not return an exception
	 @Test
	 public void testNullDataandZeroColumnIndex() {
	 try {
	 DataUtilities.calculateColumnTotal(null,0);
	 fail("No exception thrown. The expected outcome was: a thrown "
	 + "exception of type: IllegalArgumentException");
	 } catch (Exception e) {
	 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
	 }
	 }
	
	 @Test
	 public void testValidDataAndZeroColumnIndex() {
	 assertEquals("Sum returned should be 7.0", 7, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	 }
	 
	 @Test
	 public void testNullDataAndColumnIndex() {
		 // set up
		 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		 values2D = testValues;
		 testValues.addValue(null, 0, 0);
		 try {
			 DataUtilities.calculateColumnTotal(values2D,0);
			 fail("No exception thrown. The expected outcome was: a thrown "
			 + "exception of type: IllegalArgumentException");
			 } catch (Exception e) {
			 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
			}
		 }
	 
	 @Test
	 public void testValidDataAndValidPositiveColumnIndex() {
	 assertEquals("Sum returned should be 8.0", 8, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	 }
	 
	@Test
	 public void testValidDataAndValidPositiveColumnIndexWithNullValues() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(1, 0, 0);
	 testValues.addValue(null, 1, 0);
	 testValues.addValue(3, 2, 0);
	 testValues.addValue(2, 0, 1);
	 testValues.addValue(null, 1, 1);
	 testValues.addValue(4, 2, 1);
	 testValues.addValue(3, 0, 2);
	 testValues.addValue(3, 1, 2);
	 
	assertEquals("Sum returned should be 4", 4, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	 
	}
	 @Test
	 public void test0x0DataAndPositiveColumnIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 assertEquals("Sum returned should be 0.0", 0, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	 }
	 
	//Test case failed due to invalid column index -1 should return 0 for invalid input
	 @Test
	 public void test0x0DataAndNegativeColumnIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 try {
	 assertEquals("Sum returned should be 0.0", 0.0, DataUtilities.calculateColumnTotal(values2D, -1),
	 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 fail("An unexpected IndexOutOfBoundsException was thrown");
	 }
	 }
	//Test case passed
	 @Test
	 public void test0x0DataAndZeroColumnIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 try {
	 assertEquals("Sum returned should be 0.0", 0.0, DataUtilities.calculateColumnTotal(values2D, 0),
	 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 fail("An unexpected IndexOutOfBoundsException was thrown");
	 }
	 }

	 
	// rows now
	//Test case failed due to invalid column index- should return 0 for invalid input
	 @Test
	 public void testValidDataAndInValidNegativeRowIndex() {
	 try {
	 assertEquals("Sum returned should be 0", 0, DataUtilities.calculateRowTotal(values2D, -1), 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 
	}
	 }
	 
	 
	 // TC failed - method does not return an exception
	 @Test
	 public void testNullDataandRowIndex() {
	 try {
	 DataUtilities.calculateColumnTotal(null, 1);
	 fail("No exception thrown. The expected outcome was: a thrown "
	 + "exception of type: IllegalArgumentException");
	 } catch (Exception e) {
	 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
	 }
	 }
	 
	 
	//Test case passed
	@Test
	 public void testValidDataAndZeroRowIndex() {
	 assertEquals("Sum returned should be 6", 6, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	 }
	
	//Test case passed
	 @Test
	 public void testValidDataAndValidPositiveRowIndex() {
	 assertEquals("Sum returned should be 8", 8, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
	 }
	 
	 @Test
	 public void testNullDataAndRowIndex() {
		 // set up
		 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		 values2D = testValues;
		 testValues.addValue(null, 0, 0);
		 try {
			 DataUtilities.calculateRowTotal(values2D,0);
			 fail("No exception thrown. The expected outcome was: a thrown "
			 + "exception of type: IllegalArgumentException");
			 } catch (Exception e) {
			 assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
			}
		 }
	 
	 
	 @Test
	 public void test0x0DataAndPositiveRowIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 assertEquals("Sum returned should be 0.0", 0, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
	 }
	//Test case failed due to invalid column index-1 should return 0 for invalid input
	 @Test
	 public void test0x0DataAndNegativeRowIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 try {
	 assertEquals("Sum returned should be 0", 0, DataUtilities.calculateRowTotal(values2D, -1),
	 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 fail("An unexpected IndexOutOfBoundsException was thrown");
	 }
	 }
	//Test case passed
	 @Test
	 public void test0x0DataAndZeroRowIndex() {
	 // set up
	 DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
	 values2D = testValues;
	 testValues.addValue(0, 0, 0);
	 testValues.addValue(0, 1, 0);
	 testValues.addValue(0, 2, 0);
	 testValues.addValue(0, 0, 1);
	 testValues.addValue(0, 1, 1);
	 testValues.addValue(0, 2, 1);
	 testValues.addValue(0, 0, 2);
	 testValues.addValue(0, 1, 2);
	 testValues.addValue(0, 2, 2);
	 try {
	 assertEquals("Sum returned should be 0.0", 0.0, DataUtilities.calculateRowTotal(values2D, 0),
	 0.0000001d);
	 } catch (IndexOutOfBoundsException e) {
	 fail("An unexpected IndexOutOfBoundsException was thrown");
	 }
	 }
 
	 


	// Test Cases for createNumberArray
	@Test
	public void testCreateEmptyNumberArray() {
     
		double[] data = {};
		DataUtilities.createNumberArray(data);

	}

	@Test
	public void testCreateNumberArray_ValidDataObjectWithMultipleValues() {
		double[] input = { 1.0, 2.0, 3.0, 4.0 };
		Number[] expectedOutput = { 1.0, 2.0, 3.0, 4.0 };
		Number[] test = DataUtilities.createNumberArray(input);
		assertArrayEquals(expectedOutput, test);
	}

	@Test
	public void testCreateNumberArray_ValidDataObjectWithOnlyOneValuesInArray() {
		double[] input = { 1.0 };
		Number[] expectedOutput = { 1.0 };
		Number[] test = DataUtilities.createNumberArray(input);
		assertArrayEquals(expectedOutput, test);
	}

	@Test
	public void testCreateNumberArray_ValidDataObjectWithMultipleValuesNegative() {
		double[] input = { -1.0, -2.0, -3.0 };
		Number[] expectedOutput = { -1.0, -2.0, -3.0 };
		Number[] test = DataUtilities.createNumberArray(input);
		assertArrayEquals(expectedOutput, test);
	}

	@Test
	public void testCreateNumberArray_MaximumValidInputValue() {
		double[] input = new double[1000];
		Arrays.fill(input, Double.MAX_VALUE);
		Number[] expectedOutput = new Number[1000];
		Arrays.fill(expectedOutput, Double.MAX_VALUE);
		Number[] test = DataUtilities.createNumberArray(input);
		assertArrayEquals(expectedOutput,test );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArrayWithNullData() {
	    double[] data = null;
	    Number[] result = DataUtilities.createNumberArray(data);
	}


//Test Cases for 2dcreateNumberArray
	@Test
	public void testCreateNumberArray2D() {
		// Test valid input
		double[][] validData1 = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		Number[][] test = DataUtilities.createNumberArray2D(validData1);
		Number[][] expectedValidResult1 = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		assertArrayEquals(expectedValidResult1, test);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArrayWithNullData2() {
	    double[][] data = null;
	    Number[][] result = DataUtilities.createNumberArray2D(data);
	}

	@Test
	public void testCreateNumberArray2Dnegative() {
		// Test valid input
		double[][] validData1 = { { -1.0, -2.0, -3.0 }, { -4.0, -5.0, -6.0 } };
		Number[][] test = DataUtilities.createNumberArray2D(validData1);
		Number[][] expectedValidResult1 = { { -1.0, -2.0, -3.0 }, { -4.0, -5.0, -6.0 } };
		assertArrayEquals(expectedValidResult1, test);

	}

	@Test
	public void testCreateNumberArray2DInvalidDataTwoDifferentSizesOfArray() {
		double[][] data = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0 } };
		try {
			Number[][] result = DataUtilities.createNumberArray2D(data);
			fail("Expected InvalidParameterException was not thrown");
		} catch (InvalidParameterException e) {
			// expected exception
		}
	}

	@Test
	public void testCreateNumberArray2DInvalidData2EmptyArraysAssigned() {
		double[][] data = { {}, {} };
		try {
			Number[][] result = DataUtilities.createNumberArray2D(data);
			fail("Expected InvalidParameterException was not thrown");
		} catch (InvalidParameterException e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	@Test
	public void testCreateNumberArray2D_nvalidDataOnlyOneEmptyArrayAssigned() {
		double[][] invalidData1 = {};
		try {
			DataUtilities.createNumberArray2D(invalidData1);
			fail("Expected InvalidParameterException");
		} catch (InvalidParameterException e) {
			// Exception caught as expected
		}
	}

	
	  
	  
	   
	  @Test public void testValidInputForCumulativePercentagesOnlyOneValue() { // Test case with valid input
	  DefaultKeyedValues testValues = new DefaultKeyedValues(); 
	  
	  
	  testValues.addValue("A", 2); 
	   
	  KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
	  
	  assertEquals(1, result.getValue(0)); 
	  
	  }
	  @Test public void testValidInputForCumaltivePercentagesDecimalPoints() { // Test case with valid input
		  DefaultKeyedValues testValues = new DefaultKeyedValues(); 
		  
		  
		  testValues.addValue("A", 2.5); 
		  testValues.addValue("B", 2.5);
		  testValues.addValue("C", 4); 
		  KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
		  double expected = 5/9;
		  assertEquals(expected, result.getValue(1)); 
		  
		  }
	  
	  @Test public void testValidInputForCumaltivePercentagesPositiveValues() { // Test case with valid input
		  DefaultKeyedValues testValues = new DefaultKeyedValues(); 
		  
		  
		  testValues.addValue("A", 2); 
		  testValues.addValue("B", 2);
		  testValues.addValue("C", 4); 
		  KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
		  
		  assertEquals(0.5, result.getValue(2)); 
		  
		  }
	  
	  @Test public void testValidInputForCumaltivePercentagesNegativeValues() { // Test case with valid input
		  DefaultKeyedValues testValues = new DefaultKeyedValues(); 
		  
		  
		  testValues.addValue("A", -2); 
		  testValues.addValue("B", -2);
		  testValues.addValue("C", -4); 
		  KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
		  
		  assertEquals(1.0, result.getValue(2)); 
		  
		  }
	  
	  @Test(expected = IllegalArgumentException.class)
	  public void testGetCumulativePercentagesWithNullData() {
	      KeyedValues data = null;
	      KeyedValues result = DataUtilities.getCumulativePercentages(data);
	  }
	  
	  @Test public void testInvalidInputNullForCumaltivePercentages() { // Test case with valid input
		  DefaultKeyedValues testValues = new DefaultKeyedValues(); 
		  
		  try {
			  testValues.addValue("A", null); 
			  testValues.addValue("B", null);
			  testValues.addValue("C", null); 
			  KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
			  
			  assertEquals(0.25, result.getValue(0)); 
				fail("No exception thrown.");
			} catch (AssertionError  e) {
				
		  
		  
		  }
		  
		  
		  
	  
	  
	 
	 
}}
