package TDAABBB2;

import java.util.Comparator;

public class ComparableComparator<T extends Comparable<T>> implements Comparator<T> {
	  @Override
	  public int compare(T lhs, T rhs) {
	    return lhs.compareTo(rhs);
	  }  
	}
