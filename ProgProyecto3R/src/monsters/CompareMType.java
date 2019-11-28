package monsters;

import java.util.Comparator;

public class CompareMType implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (a.getTypeString().compareTo(b.getTypeString())==0) {
			CompareMName comp = new CompareMName();
			return comp.compare(a, b);
		}
		
		return a.getTypeString().compareTo(b.getTypeString());
	}

}