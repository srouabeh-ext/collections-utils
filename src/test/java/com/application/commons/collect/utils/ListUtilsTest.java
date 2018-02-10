package com.application.commons.collect.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * Unit Test For {@link ListUtils}
 * 
 * @author Skander ROUABEH
 * @version 0.0.1
 * @since 1.8
 *
 */
public class ListUtilsTest {

	private static List<Integer> inputList;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Initialize Input List.
	 */
	@BeforeClass
	public static void init() {
		inputList = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			inputList.add(i);
		}
	}

	@Test
	public void test_partition_null_input_list() {
		// Given
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Input List should not be null");
		final int subListMaxSize = 1;
		// When
		ListUtils.partition(null, subListMaxSize);
		// Then - IllegalArgumentException expected
	}

	@Test
	public void test_partition_by_negative_or_zero() {
		// Given
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Sub list max size should not be negative or zero");
		final int subListMaxSize = 0;
		// When
		ListUtils.partition(inputList, subListMaxSize);
		// Then - IllegalArgumentException expected
	}

	@Test
	public void test_partition_empty_input_list() {
		// Given
		final List<Integer> emptyList = new ArrayList<Integer>();
		final int subListMaxSize = 2;
		// When
		List<List<Integer>> partition = ListUtils.partition(emptyList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertTrue(partition.isEmpty());
	}

	@Test
	public void test_partition_by_one() {
		// Given
		final int subListMaxSize = 1;
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(inputList.size(), partition.size()); // partition = [[0], [1], [2], [3], [4]]
		assertEquals(partition.get(0), Arrays.asList(0));
		assertEquals(partition.get(1), Arrays.asList(1));
		assertEquals(partition.get(2), Arrays.asList(2));
		assertEquals(partition.get(3), Arrays.asList(3));
		assertEquals(partition.get(4), Arrays.asList(4));
	}

	@Test
	public void test_partition_by_two() {
		// Given
		final int subListMaxSize = 2;
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(3, partition.size()); // partition = [[0, 1], [2, 3], [4]]
		assertEquals(partition.get(0), Arrays.asList(0, 1));
		assertEquals(partition.get(1), Arrays.asList(2, 3));
		assertEquals(partition.get(2), Arrays.asList(4));
	}

	@Test
	public void test_partition_by_input_list_size() {
		// Given
		final int subListMaxSize = inputList.size();
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(1, partition.size());
		assertEquals(inputList, partition.get(0)); // partition = [[0, 1, 2, 3, 4]]
	}

	@Test
	public void test_partition_by_size_greater_than_input_list_size() {
		// Given
		final int subListMaxSize = inputList.size() + 1;
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(1, partition.size());
		assertEquals(inputList, partition.get(0)); // partition = [[0, 1, 2, 3, 4]]
	}

	/**
	 * Check if the subLists are not a view of the input list. <br>
	 * Input list unmodified if we modify a subList content.
	 */
	@Test
	public void test_partition_and_check_unmodifiable_input_list() {
		// Given
		final int subListMaxSize = 2;
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(3, partition.size());
		// And When
		partition.get(2).add(5); // partition = [[0, 1], [2, 3], [4, 5]]
		// Then
		assertEquals(5, inputList.size()); // inputList = [0, 1, 2, 3, 4] and not [0, 1, 2, 3, 4, 5]
	}

}
