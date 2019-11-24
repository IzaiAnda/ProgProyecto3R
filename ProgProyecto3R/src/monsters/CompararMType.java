package monsters;

import java.util.Comparator;

public class CompararMType implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (a.getTypeString().compareTo(b.getTypeString())==0) {
			CompararMName comp = new CompararMName();
			return comp.compare(a, b);
		}
		
		return a.getTypeString().compareTo(b.getTypeString());
	}

}