package monsters;

public class MonsterWater extends Monster{

	public String type = "Water";

	public MonsterWater(String name, int hP, int atk, int def, int vel) {
		super(name, hP, atk, def, vel);
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public boolean efectividad(String type) {
		if(type == "Plant") {
			return true;
		}
		return false;
	}
	
}
