package monsters;

import java.util.Comparator;

public class CompareMDefense implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getDefense(), a.getDefense())==0) {
			CompareMName comp = new CompareMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(a.getDefense(), a.getDefense());
	}

}