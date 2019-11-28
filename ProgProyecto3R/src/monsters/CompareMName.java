package monsters;

import java.util.Comparator;

public class CompareMName implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		return a.getName().compareTo(b.getName());
	}

}
