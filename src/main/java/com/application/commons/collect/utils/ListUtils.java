package com.application.commons.collect.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides static utility methods used for {@link List} instances.
 * 
 * @author Skander ROUABEH
 * @version 0.0.1
 * @since 1.8
 *
 */
public final class ListUtils {

	private static final String NULL_INPUT_LIST_MSG = "Input List should not be null";
	private static final String NEGATIVE_SUB_LIST_MAX_SIZE_MSG = "Sub list max size should not be negative or zero";

	/**
	 * {@link ListUtils} should not be instantiated.
	 */
	private ListUtils() {
	}

	/**
	 * Partition the {@link List} {@code inputList} into {@code subLists} using the
	 * {@code subListMaxSize} parameter.
	 * <p>
	 * For example, partitioning {@code [1, 2, 3, 4, 5]} with a
	 * {@code subListMaxSize} of 2 will return {@code [[1, 2], [3, 4], [5]]}
	 * 
	 * @param <T>
	 *            The object type.
	 * @param inputList
	 *            - The original list.
	 * @param subListMaxSize
	 *            - The subList max size.
	 * @return List of consecutive {@code subLists} composed from a split of
	 *         {@code inputList} using {@code subListMaxSize} parameter.
	 * @throws IllegalArgumentException
	 *             if {@code inputList} is null or if {@code subListMaxSize} is
	 *             negative or 0.
	 */
	public static <T> List<List<T>> partition(final List<T> inputList, final int subListMaxSize) {

		checkArguments(null == inputList, NULL_INPUT_LIST_MSG);
		checkArguments(subListMaxSize <= 0, NEGATIVE_SUB_LIST_MAX_SIZE_MSG);

		final List<List<T>> listOfSubLists = new ArrayList<>();

		List<T> subList = new ArrayList<>();
		int index = 1;
		final Iterator<T> iterator = inputList.iterator();
		while (iterator.hasNext()) {
			subList.add(iterator.next());
			if (index % subListMaxSize == 0) {
				listOfSubLists.add(subList);
				subList = new ArrayList<>();
			} else if (index == inputList.size()) {
				listOfSubLists.add(subList);
			}
			index++;
		}
		return listOfSubLists;
	}

	private static void checkArguments(final boolean isInvalidArgument, final String errorMessage) {
		if (isInvalidArgument)
			throw new IllegalArgumentException(errorMessage);
	}

}
