package monsters;

public class MonsterFire extends Monster {
	
	public String type = "Fire";

	public MonsterFire(String name, int hP, int atk, int def, int vel) {
		super(name, hP, atk, def, vel);
		this.type = "Fire";
	}

	@Override
	public boolean efectividad(String type) {
		if(type == "Water") {
			return true;
		}
		return false;
	}
	
	
	
}
