
public class Monster {
	
	private String name;
	private int HP;
	private int atk;
	private int def;
	private int vel;

	public Monster(String name, int hP, int atk, int def, int vel) {
		super();
		this.name = name;
		this.HP = hP;
		this.atk = atk;
		this.def = def;
		this.vel = vel;
	}
	
	public Monster() {
		super();
		this.name = "";
		this.HP = 0;
		this.atk = 0;
		this.def = 0;
		this.vel = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	@Override
	public String toString() {
		return "Monster [" + name + ", " + HP + ", " + atk + ", " + def + ", " + vel + "]";
	}
	
	
}
