package baseJuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import monsters.Monster;
import monsters.Monster.Type;
import monsters.MonsterFire;
import monsters.MonsterPlant;
import monsters.MonsterWater;
import moves.Move;

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

	public static void closeBD() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(String tName, String code) { // public String insert(String login, password, creation) {

		try {

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

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.out.println(e);
			System.exit(0);
		}
		logger.log(Level.FINER, "Records created successfully");

	}

	public void create(String tName, String code) {
		try {

			stmt = c.createStatement();
			String sql = "CREATE TABLE " + tName + "(" + code + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	

	public static void create() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE PLAYER ( NAME_P VARCHAR(30) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(30) NOT NULL);";
			String sql1 = "CREATE TABLE MONSTER ( NAME_M VARCHAR(30) NOT NULL PRIMARY KEY,TYPE VARCHAR(30) NOT NULL, HP INTEGER NOT NULL, ATK INTEGER NOT NULL, DEF INTEGER NOT NULL, VEL INTEGER NOT NULL);";
			String sql2 = "CREATE TABLE MOVE ( NAME_MOV VARCHAR(30) NOT NULL PRIMARY KEY,DMG INTEGER NOT NULL);";
			String sql3 = "CREATE TABLE MM ( NAME_M VARCHAR(30) NOT NULL, NAME_MOV VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_M, NAME_MOV), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_MOV) REFERENCES MOVE(NAME_MOV));";
			String sql4 = "CREATE TABLE LEVEL ( NAME_L VARCHAR(30) NOT NULL PRIMARY KEY,TXT VARCHAR(30) NOT NULL);";
			String sql5 = "CREATE TABLE LM ( NAME_L VARCHAR(30) NOT NULL,NAME_M VARCHAR(30) NOT NULL, PI INTEGER NOT NULL, PRIMARY KEY (NAME_L, NAME_M), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_L) REFERENCES LEVEL(NAME_L));";
			String sql6 = "CREATE TABLE LP ( NAME_P VARCHAR(30) NOT NULL,NAME_L VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_P, NAME_L), FOREIGN KEY (NAME_P) REFERENCES PLAYER(NAME_P),FOREIGN KEY (NAME_L) REFERENCES LEVEL(NAME_L));";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql5);
			stmt.executeUpdate(sql4);
			stmt.executeUpdate(sql6);
			stmt.close();
			c.commit();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public String select(String code) {
		String i = "No hay nada";
		try {

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + code + ";");

			i = rs.getString(1);

			rs.close();
			stmt.close();

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
			stmt2 = c.prepareStatement("SELECT NAME_P,PASSWORD FROM PLAYER WHERE NAME_P=? AND PASSWORD=?");
			stmt2.setString(1, nom);
			stmt2.setString(2, pass);
			ResultSet rs = stmt2.executeQuery();
			if (rs.getString(1).equals(nom) && rs.getString(2).equals(pass)) {
				i = true;
			}
			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return i;
	}

	public static void createJugador(String nom, String pass) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO PLAYER (NAME_P,PASSWORD) VALUES (?, ?)");
			stmt2.setString(1, nom);
			stmt2.setString(2, pass);
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void createMonster(Monster mon) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO MONSTER (NAME_M,TYPE,HP,ATK,DEF,VEL) VALUES (?,?,?,?,?,?)");
			stmt2.setString(1, mon.getName());
			stmt2.setString(2, mon.getTypeString());
			stmt2.setInt(3, mon.getLifePoints());
			stmt2.setInt(4, mon.getAttack());
			stmt2.setInt(5, mon.getDefense());
			stmt2.setInt(6, mon.getSpeed());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void createAllMonsters(List<? extends Monster> lista) {

		for (Monster monster : lista) {
			createMonster(monster);
		}

	}
	
	public static void createMove(Move move) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO MOVE (NAME_MOV,DMG) VALUES (?,?)");
			stmt2.setString(1, move.getName());
			stmt2.setInt(2,move.getDamage());
			
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createAllMoves(List<? extends Move> lista) {

		for (Move move : lista) {
			createMove(move);
		}

	}

	// public static Monster selectMonster (String nom) {
	// Monster i = new Monster();
	// try {
	//
	// stmt2 = c.prepareStatement("SELECT * FROM MONSTER WHERE NAME_M = ? ");
	// stmt2.setString(1, nom);
	//
	// ResultSet rs = stmt2.executeQuery();
	//
	// i.setName(rs.getString(1));
	// i.setHP(rs.getInt(3));
	// i.setAtk(rs.getInt(4));
	// i.setDef(rs.getInt(5));
	// i.setVel(rs.getInt(6));
	//
	// stmt2.close();
	//
	// } catch (Exception e) {
	// System.out.println(e.getClass().getName() + ": " + e.getMessage());
	// }
	// return i;
	// }

	public static LinkedList<Monster> selectAllMonsters() {
		LinkedList<Monster> s = new LinkedList<>();
		try {

			stmt2 = c.prepareStatement("SELECT * FROM MONSTER");
			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {

				String name = rs.getString(1);
				String type = rs.getString(2);
				int lifePoints = rs.getInt(3);
				int attack = rs.getInt(4);
				int defense = rs.getInt(5);
				int speed = rs.getInt(6);

				if (type.equals(Type.WATER.toString())) {
					s.add(new MonsterWater(name, lifePoints, attack, defense, speed));
				} else if (type.equals(Type.FIRE.toString())) {
					s.add(new MonsterFire(name, lifePoints, attack, defense, speed));
				} else if (type.equals(Type.PLANT.toString())) {
					s.add(new MonsterPlant(name, lifePoints, attack, defense, speed));
				}
			}

			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}

	public static void main(String[] args) throws SQLException {
		startBD();
		create();
		LinkedList<Monster> bdMonter = new LinkedList<>();
		LinkedList<Move> bdMove = new LinkedList<>();

		MonsterPlant catercute = new MonsterPlant("Catercute", 100, 100, 100, 100);
		MonsterPlant weepinutor = new MonsterPlant("Weepinutor", 10, 10, 10, 10);
		MonsterPlant venulax = new MonsterPlant("Venulax", 10, 10, 10, 10);

		MonsterFire moltnx = new MonsterFire("Moltnx", 10, 10, 10, 10);
		MonsterFire charas = new MonsterFire("Charas", 10, 10, 10, 10);
		MonsterFire tangizard = new MonsterFire("Tangizard", 10, 10, 10, 10);

		MonsterWater meowcruel = new MonsterWater("Meowcruel", 50, 50, 50, 50);
		MonsterWater seesect = new MonsterWater("Seesect", 50, 50, 50, 50);
		MonsterWater mutoise = new MonsterWater("Mutoise", 50, 50, 50, 50);

		bdMonter.add(catercute);
		bdMonter.add(weepinutor);
		bdMonter.add(venulax);

		bdMonter.add(moltnx);
		bdMonter.add(charas);
		bdMonter.add(tangizard);

		bdMonter.add(meowcruel);
		bdMonter.add(seesect);
		bdMonter.add(mutoise);

		createAllMonsters(bdMonter);
		
		Move ascuas = new Move("Ascuas", 75);
		Move lluevehojas = new Move("LlueveHojas", 75);
		Move pistolaAgua = new Move("Pistola Agua", 75);

		
		bdMove.add(ascuas);
		bdMove.add(lluevehojas);
		bdMove.add(pistolaAgua);

		
		createAllMoves(bdMove);

	}

}
