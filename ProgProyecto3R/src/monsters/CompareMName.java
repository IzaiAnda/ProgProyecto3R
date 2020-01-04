package monsters;

import java.util.Comparator;

import moves.Move;

public class CompareMName implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		return a.getName().compareTo(b.getName());
	}

	public int compareName(Move a, Move b) {
		// TODO Auto-generated method stub
		return a.getName().compareTo(b.getName());
	}

}
