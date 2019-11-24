package monsters;

import java.util.Comparator;

public class CompararMAttack implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getAttack(), a.getAttack())==0) {
			CompararMName comp = new CompararMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(a.getAttack(), a.getAttack());
	}

}
