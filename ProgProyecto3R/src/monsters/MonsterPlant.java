package monsters;

public class MonsterPlant extends Monster{

	public String type = "Plant";

	public MonsterPlant(String name, int hP, int atk, int def, int vel) {
		super(name, hP, atk, def, vel);
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public boolean efectividad(String type) {
		if(type == "Fire") {
			return true;
		}
		return false;
	}
	
}
