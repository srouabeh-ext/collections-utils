# collections-utils
Static utility methods used for java collections

<b>1- Partition Method:</b>

  public static <T> java.util.List<java.util.List<T>> partition(java.util.List<T> inputList,
                                                              int subListMaxSize)
      Partition the List inputList into subLists using the subListMaxSize parameter.
      <br><br>For example:
       <br>partitioning [1, 2, 3, 4, 5] with a subListMaxSize of 1 will return [[1], [2], [3], [4], [5]].
       <br>partitioning [1, 2, 3, 4, 5] with a subListMaxSize of 2 will return [[1, 2], [3, 4], [5]].
       <br>partitioning [1, 2, 3, 4, 5] with a subListMaxSize of 3 will return [[1, 2, 3], [4, 5]].

      Type Parameters:
        T - The object type.
      Parameters:
        inputList - The original list.
        subListMaxSize - The subList max size.
      Returns:
        List of consecutive subLists composed from a split of inputList using subListMaxSize parameter.
      Throws:
        java.lang.IllegalArgumentException - if inputList is null or if subListMaxSize is negative or 0.
