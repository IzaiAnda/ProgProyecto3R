package monsters;

import java.util.Comparator;

public class CompareMAttack implements Comparator<Monster>{

	@Override
	public int compare(Monster a, Monster b) {
		// TODO Auto-generated method stub
		
		if (Integer.compare(a.getAttack(), b.getAttack())==0) {
			CompareMName comp = new CompareMName();
			return comp.compare(a, b);
		}
		
		return Integer.compare(b.getAttack(), a.getAttack());
	}

}
