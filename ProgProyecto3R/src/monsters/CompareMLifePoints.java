package monsters;

import java.util.Comparator;

public class CompareMLifePoints implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub

		if (Integer.compare(a.getLifePoints(), b.getLifePoints())==0) {
			CompareMName comp = new CompareMName();
			return comp.compare(a, b);
		}

		return Integer.compare(b.getLifePoints(), a.getLifePoints());
	}

}
