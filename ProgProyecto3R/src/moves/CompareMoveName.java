package moves;

import java.util.Comparator;

public class CompareMoveName implements Comparator<Move>{

	@Override
	public int compare(Move a, Move b) {
		// TODO Auto-generated method stub
		return a.getName().compareTo(b.getName());
	}


}
