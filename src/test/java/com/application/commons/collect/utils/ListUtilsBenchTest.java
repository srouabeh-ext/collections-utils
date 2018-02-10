package com.application.commons.collect.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

/**
 * 
 * Benchmarks Unit Test For
 * {@link ListUtils#partition(List inputList, int subListMaxSize)} method with
 * {@link ArrayList}, {@link Vector} and {@link LinkedList} instances.
 * 
 * @author Skander ROUABEH
 * @version 0.0.1
 * @since 1.8
 *
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "benchmark-list-partition")
public class ListUtilsBenchTest {

	private static List<Integer> inputArrayList;
	private static List<Integer> inputVectorList;
	private static List<Integer> inputLinkedList;

	@Rule
	public TestRule benchmarkRule = new BenchmarkRule();

	/**
	 * Initialize List instances with 1000000 element.
	 */
	@BeforeClass
	public static void init() {
		inputArrayList = new ArrayList<Integer>();
		inputVectorList = new Vector<>();
		inputLinkedList = new LinkedList<>();
		for (int i = 0; i < 1000000; i++) {
			inputArrayList.add(i);
			inputVectorList.add(i);
			inputLinkedList.add(i);
		}
	}

	@Test
	public void test_bench_partition_array_list() {
		bench(inputArrayList);
	}

	@Test
	public void test_bench_partition_vector() {
		bench(inputVectorList);
	}

	@Test
	public void test_bench_partition_linked_list() {
		bench(inputLinkedList);
	}

	private void bench(final List<Integer> inputList) {
		// Given
		final int subListMaxSize = 1;
		// When
		final List<List<Integer>> partition = ListUtils.partition(inputList, subListMaxSize);
		// Then
		assertNotNull(partition);
		assertEquals(inputList.size(), partition.size());
	}

}
