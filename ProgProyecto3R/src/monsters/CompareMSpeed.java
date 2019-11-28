package monsters;

import java.util.Comparator;

public class CompareMSpeed implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getSpeed(), a.getSpeed())==0) {
			CompareMName comp = new CompareMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(a.getSpeed(), a.getSpeed());
	}

}