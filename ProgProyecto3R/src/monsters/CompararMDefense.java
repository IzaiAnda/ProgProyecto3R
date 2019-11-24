package monsters;

import java.util.Comparator;

public class CompararMDefense implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getDefense(), a.getDefense())==0) {
			CompararMName comp = new CompararMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(a.getDefense(), a.getDefense());
	}

}