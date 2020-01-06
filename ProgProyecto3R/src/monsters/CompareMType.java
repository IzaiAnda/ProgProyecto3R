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
	
	public static void main(String[] args) {
		Monster a = new MonsterPlant("pepe", 10, 10, 10, 10);
		Monster b = new MonsterFire("Juan", 12, 12, 12, 12);
		Monster d = new MonsterFire("Juan", 12, 12, 12, 12);
		
		CompareMType c = new CompareMType();
		
		System.out.println(b.getTypeString().compareTo(a.getTypeString()));
		System.out.println(c.compare(a,b));
		System.out.println(c.compare(d, b));
	}
	
}