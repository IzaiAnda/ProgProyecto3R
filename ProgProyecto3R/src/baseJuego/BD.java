package baseJuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import monsters.Monster;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;
import monsters.Monster.Type;

public class BD {

	static Connection c = null;
	static Statement stmt = null;
	static PreparedStatement stmt2 = null;
	private static Logger logger = Logger.getLogger("LoggerBD");

	static {
		try {
			logger.setLevel(Level.FINEST);
			logger.addHandler(new FileHandler("LoggerBD.xml"));
		} catch (Exception e) {
		}
	}

	public static void startBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Deusmon.db");
			c.setAutoCommit(false);
			logger.log(Level.FINER, "Opened database successfully");
		} catch (Exception e) {

		}
	}

	public void insert(String tName, String code) { // public String insert(String login, password, creation) {

		try {
			startBD();

			stmt = c.createStatement();
			String sql = "INSERT INTO " + tName + " VALUES " + code + ";";

			// PreparedStatement stmt = conn.prepareStatement("INSERT INTO USUARIO
			// (LOGIN,PASSWORD,CREATION_DATE) VALUES (?,?,?)")
			// stmt.setString(1, login)
			// stmt.setString(1, password)
			// stmt.setString(1, creation)
			// stmt.executateUpdate();

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.out.println(e);
			System.exit(0);
		}
		logger.log(Level.FINER, "Records created successfully");

	}

	public void create(String tName, String code) {
		try {
			startBD();
			stmt = c.createStatement();
			String sql = "CREATE TABLE " + tName + "(" + code + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void create() {
		try {
			startBD();
			stmt = c.createStatement();
			// String sql = "CREATE TABLE PLAYER ( NAME_P VARCHAR(30) NOT NULL PRIMARY
			// KEY,PASSWORD VARCHAR(30) NOT NULL);";
			// String sql = "CREATE TABLE MONSTER ( NAME_M VARCHAR(30) NOT NULL PRIMARY
			// KEY,TYPE VARCHAR(30) NOT NULL, HP INTEGER NOT NULL, ATK INTEGER NOT NULL, DEF
			// INTEGER NOT NULL, VEL INTEGER NOT NULL);";
			// String sql = "CREATE TABLE MOVE ( NAME_MOV VARCHAR(30) NOT NULL PRIMARY
			// KEY,TYPE VARCHAR(30) NOT NULL, DMG INTEGER NOT NULL, DESCRIPTION VARCHAR(30),
			// PERCENT INTEGER);";
			// String sql = "CREATE TABLE MM ( NAME_M VARCHAR(30) NOT NULL, NAME_MOV
			// VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_M, NAME_MOV), FOREIGN KEY (NAME_M)
			// REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_MOV) REFERENCES
			// MOVE(NAME_MOV));";
			// String sql = "CREATE TABLE LEVEL ( NAME_L VARCHAR(30) NOT NULL PRIMARY
			// KEY,TXT VARCHAR(30) NOT NULL);";
			// String sql = "CREATE TABLE LM ( NAME_L VARCHAR(30) NOT NULL,NAME_M
			// VARCHAR(30) NOT NULL, PI INTEGER NOT NULL, PRIMARY KEY (NAME_L, NAME_M),
			// FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_L)
			// REFERENCES LEVEL(NAME_L));";
			String sql = "CREATE TABLE LP ( NAME_P VARCHAR(30) NOT NULL,NAME_L VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_P, NAME_L), FOREIGN KEY (NAME_P) REFERENCES PLAYER(NAME_P),FOREIGN KEY (NAME_L) REFERENCES LEVEL(NAME_L));";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public String select(String code) {
		String i = "No hay nada";
		try {
			startBD();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + code + ";");

			i = rs.getString(1);

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		logger.log(Level.FINER, "Operation done successfully");

		return i;
	}

	public static boolean selectJugador(String nom, String pass) {
		boolean i = false;
		try {
			startBD();
			stmt2 = c.prepareStatement("SELECT NAME_P,PASSWORD FROM PLAYER WHERE NAME_P = ? AND PASSWORD = ?");
			stmt2.setString(1, nom);
			stmt2.setString(2, pass);
			ResultSet rs = stmt2.executeQuery();
			if (rs.getString(1).equals(nom) && rs.getString(2).equals(pass)) {
				i = true;
			}
			stmt2.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return i;
	}

	public static void createJugador(String nom, String pass) {
		try {
			startBD();
			stmt2 = c.prepareStatement("INSERT INTO PLAYER (NAME_P,PASSWORD) VALUES (?, ?)");
			stmt2.setString(1, nom);
			stmt2.setString(2, pass);
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createMonster(MonsterPlant mon) {
		try {
			startBD();
			stmt2 = c.prepareStatement("INSERT INTO MONSTER (NAME_M,TYPE,HP,ATK,DEF,VEL) VALUES (?,?,?,?,?,?)");
			stmt2.setString(1, mon.getName());
			stmt2.setString(2, "PLANT");
			stmt2.setInt(3, mon.getlifePoints());
			stmt2.setInt(4, mon.getattack());
			stmt2.setInt(5, mon.getdefense());
			stmt2.setInt(6, mon.getspeed());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createMonster(MonsterFire mon) {
		try {
			startBD();
			stmt2 = c.prepareStatement("INSERT INTO MONSTER (NAME_M,TYPE,HP,ATK,DEF,VEL) VALUES (?,?,?,?,?,?)");
			stmt2.setString(1, mon.getName());
			stmt2.setString(2, "FIRE");
			stmt2.setInt(3, mon.getlifePoints());
			stmt2.setInt(4, mon.getattack());
			stmt2.setInt(5, mon.getdefense());
			stmt2.setInt(6, mon.getspeed());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createMonster(MonsterWater mon) {
		try {
			startBD();
			stmt2 = c.prepareStatement("INSERT INTO MONSTER (NAME_M,TYPE,HP,ATK,DEF,VEL) VALUES (?,?,?,?,?,?)");
			stmt2.setString(1, mon.getName());
			stmt2.setString(2, "WATER");
			stmt2.setInt(3, mon.getlifePoints());
			stmt2.setInt(4, mon.getattack());
			stmt2.setInt(5, mon.getdefense());
			stmt2.setInt(6, mon.getspeed());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	
	
	//	public static Monster selectMonster (String nom) {
	//		Monster i = new Monster();
	//		try {
	//			startBD();
	//			stmt2 = c.prepareStatement("SELECT * FROM MONSTER WHERE NAME_M = ? ");
	//			stmt2.setString(1, nom);
	//	
	//			ResultSet rs = stmt2.executeQuery();
	//			
	//			i.setName(rs.getString(1));
	//			i.setHP(rs.getInt(3));
	//			i.setAtk(rs.getInt(4));
	//			i.setDef(rs.getInt(5));
	//			i.setVel(rs.getInt(6));
	//			
	//			stmt2.close();
	//			c.close();
	//		} catch (Exception e) {
	//			System.out.println(e.getClass().getName() + ": " + e.getMessage());
	//		}
	//		return i;	
	//	}

	public static LinkedList selectAllMonsters () {
		LinkedList<Monster> s = new LinkedList();
		try {
			startBD();
			stmt2 = c.prepareStatement("SELECT * FROM MONSTER");
			ResultSet rs = stmt2.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			while (rs.next()) {

					String name = rs.getString(1);
					String type = rs.getString(2);
					int lifePoints = rs.getInt(3);
					int attack = rs.getInt(4);
					int defense = rs.getInt(5);
					int speed = rs.getInt(6);
					
					if (type.equals("WATER")) {
						s.add(new MonsterWater(name, lifePoints, attack, defense, speed));
					}else if (type.equals("FIRE")) {
						s.add(new MonsterFire(name, lifePoints, attack, defense, speed));
					}else if (type.equals("PLANT")) {
						s.add(new MonsterPlant(name, lifePoints, attack, defense, speed));
					}
			}

			stmt2.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}


		return s;
	}

	public static void main(String[] args) throws SQLException {
		BD b = new BD();

		// b.create();
		// b.insert("PLAYER(NAME_P, PASSWORD)", "('IZAI','123')");
		// b.insert("LEVEL(NAME_L, TXT)", "('MONTAÑA1','ES LA HORA DE LUCHAR')");
		// b.insert("LP", "('IZA','MONTAÑA1')");
		// System.out.println(b.select("PLAYER"));
		MonsterPlant mon = new MonsterPlant("Plantita",100,100,100,100);
		MonsterFire mon2 = new MonsterFire("Fuegillo",10,10,10,10);
		MonsterWater mon3 = new MonsterWater("Gotita",50,50,50,50);
		createMonster(mon2);
		createMonster(mon3);
		// System.out.println(selectMonster("Popeye"));
		LinkedList<Monster> s = selectAllMonsters();
		System.out.println(s.get(0));
		System.out.println(s.get(1));
		System.out.println(s.get(2));
		
		//System.out.println(selectJugador("KEVIN", "PAPAYA"));

	}

}
