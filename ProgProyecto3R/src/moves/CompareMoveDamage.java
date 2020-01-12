package moves;

import java.util.Comparator;

import moves.CompareMoveName;

public class CompareMoveDamage implements Comparator<Move>{

	@Override
	public int compare(Move a, Move b) {
		// TODO Auto-generated method stub

		if (Integer.compare(a.getDamage(), b.getDamage())==0) {
			CompareMoveName comp = new CompareMoveName();
			return comp.compare(a, b);
		}

		return Integer.compare(b.getDamage(), a.getDamage());
	}

}
