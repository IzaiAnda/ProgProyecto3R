package monsters;

import java.util.Comparator;

public class CompararMSpeed implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getSpeed(), a.getSpeed())==0) {
			CompararMName comp = new CompararMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(a.getSpeed(), a.getSpeed());
	}

}