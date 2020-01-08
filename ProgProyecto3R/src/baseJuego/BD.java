package baseJuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import level.Enemy;
import level.LevelGame;
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

	// LA IDEA ES QUE AYA UN INT EN TU PERSONAJE QUE TE DIGA QUE NIVEL ERES, DEPENDE DE TU NIVEL CARGAS LOS V	INELES ANTERIORES.
	public static void create() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE PLAYER ( NAME_P VARCHAR(30) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(30) NOT NULL, LEVEL_P INTEGER NOT NULL);";
			String sql1 = "CREATE TABLE MONSTER ( NAME_M VARCHAR(30) NOT NULL PRIMARY KEY,TYPE VARCHAR(30) NOT NULL, HP INTEGER NOT NULL, ATK INTEGER NOT NULL, DEF INTEGER NOT NULL, VEL INTEGER NOT NULL);";
			String sql2 = "CREATE TABLE MOVE ( NAME_MOV VARCHAR(30) NOT NULL PRIMARY KEY,DMG INTEGER NOT NULL);";
			String sql3 = "CREATE TABLE MM ( NAME_M VARCHAR(30) NOT NULL, NAME_MOV VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_M, NAME_MOV), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_MOV) REFERENCES MOVE(NAME_MOV));";
			String sql4 = "CREATE TABLE ENEMY ( NAME_E VARCHAR(30) NOT NULL PRIMARY KEY,TXT VARCHAR(30) NOT NULL);";
			String sql5 = "CREATE TABLE LEVEL ( NAME_L VARCHAR(30) NOT NULL, NAME_E VARCHAR(30) NOT NULL,NAME_M VARCHAR(30) NOT NULL,DIFFICULTY INTEGER NOT NULL ,PRIMARY KEY (NAME_L,NAME_E,NAME_M), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_E) REFERENCES ENEMY(NAME_E));";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql5);
			stmt.executeUpdate(sql4);
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

			stmt2 = c.prepareStatement("INSERT INTO PLAYER (NAME_P,PASSWORD,LEVEL_P) VALUES (?, ?,?)");
			stmt2.setString(1, nom);
			stmt2.setString(2, pass);
			stmt2.setInt(3, 1);
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

	public static LinkedList<Move> selectMove(String nomMov, LinkedList<Move> s) {
		try {

			stmt2 = c.prepareStatement("SELECT * FROM MOVE WHERE NAME_MOV=?");
			stmt2.setString(1, nomMov);
			ResultSet rs = stmt2.executeQuery();

			String name = rs.getString(1);
			int damage = rs.getInt(2);

			s.add(new Move(name, damage));

			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}
	
	public static Monster selectMonster(String nomM) {
		
		Monster s = null;
		
		try {

			stmt2 = c.prepareStatement("SELECT * FROM MONSTER WHERE NAME_M=? ");
			stmt2.setString(1, nomM);
			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {

				String name = rs.getString(1);
				String type = rs.getString(2);
				int lifePoints = rs.getInt(3);
				int attack = rs.getInt(4);
				int defense = rs.getInt(5);
				int speed = rs.getInt(6);

				if (type.equals(Type.WATER.toString())) {
					s = new MonsterWater(name, lifePoints, attack, defense, speed);
				} else if (type.equals(Type.FIRE.toString())) {
					s = new MonsterFire(name, lifePoints, attack, defense, speed);
				} else if (type.equals(Type.PLANT.toString())) {
					s = new MonsterPlant(name, lifePoints, attack, defense, speed);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}

	public static LinkedList<Move> selectAllMoves() {
		LinkedList<Move> s = new LinkedList<>();
		try {

			stmt2 = c.prepareStatement("SELECT * FROM MOVE");
			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {

				String name = rs.getString(1);
				int damage = rs.getInt(2);

				s.add(new Move(name, damage));

			}

			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}

	public static void createMM(String nomMon, String nomMov) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO MM (NAME_M,NAME_MOV) VALUES (?, ?)");
			stmt2.setString(1, nomMon);
			stmt2.setString(2, nomMov);
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static LinkedList<Move> selectMM(String nomMon) {
		LinkedList<String> names = new LinkedList<>();
		LinkedList<Move> s = new LinkedList<>();
		try {
			stmt2 = c.prepareStatement("SELECT NAME_M,NAME_MOV FROM MM WHERE NAME_M=?");
			stmt2.setString(1, nomMon);
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				names.add(new String(rs.getString(2)));	
			}

			stmt2.close();

			for (int i = 0; i < names.size(); i++) {
				s = selectMove(names.get(i), s);
			}

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());

		}
		return s;
	}

	
	public static void createEnemy(String nom, String txt) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO ENEMY (NAME_E,TXT) VALUES (?, ?)");
			stmt2.setString(1, nom);
			stmt2.setString(2, txt);
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void createEnemy(Enemy en) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO ENEMY (NAME_E,TXT) VALUES (?, ?)");
			stmt2.setString(1, en.getName());
			stmt2.setString(2, en.getText());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static Enemy selectEnemy(String name) {
		Enemy s = new Enemy();
		try {

			stmt2 = c.prepareStatement("SELECT NAME_E,TXT FROM ENEMY WHERE NAME_E=?");
			stmt2.setString(1, name);
			ResultSet rs = stmt2.executeQuery();
			
			s = new Enemy(rs.getString(1),rs.getString(2));
			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}
	
	
	public static void createLevel(LevelGame level) {
		try {

			
			for (int j = 0; j < level.getMonsters().size(); j++) {
				stmt2 = c.prepareStatement("INSERT INTO LEVEL (NAME_L,NAME_E,NAME_M,DIFFICULTY) VALUES (?, ?, ?, ?)");
				stmt2.setString(1, level.getName());
				stmt2.setString(2, level.getEnemy().getName());
				stmt2.setString(3, level.getMonsters().get(j).getName());
				stmt2.setInt(4, level.getDifficulty());
				stmt2.executeUpdate();
				stmt2.close();
				c.commit();
			}



		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	String sql5 = "NAME_L,NAME_E,NAME_M,DIFFICULTY";
	
	public static LinkedList<LevelGame> selectAllLevelsUnder(int level) {
		LinkedList<LevelGame> s = new LinkedList<>();
		HashMap<String, LevelGame> training = new HashMap<>();
		try {

			stmt2 = c.prepareStatement("SELECT * FROM LEVEL WHERE DIFFICULTY<=?");
			stmt2.setInt(1, level);
			ResultSet rs = stmt2.executeQuery();
			
			while (rs.next()) {
				if (training.containsKey(rs.getObject(1))) {
					training.get(rs.getString(1)).addMonster(selectMonster(rs.getString(3)));
				}else{
					training.put(rs.getString(1), new LevelGame(rs.getString(1), selectEnemy(rs.getString(2)), new ArrayList<Monster>(), rs.getInt(4)));
				}
			}

			stmt2.close();

		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		Iterator it = training.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        s.add((LevelGame) pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
		return s;
	}

	public static void main(String[] args) throws SQLException {
		startBD();

		//create();
		
		LinkedList<Monster> bdMonter = new LinkedList<>();
		LinkedList<Move> bdMove = new LinkedList<>();

		createJugador("kevin", "kevin");

		MonsterPlant catercute = new MonsterPlant("Catercute", 100, 100, 100, 100);
		MonsterPlant weepinutor = new MonsterPlant("Weepinutor", 10, 10, 10, 10);
		MonsterPlant venulax = new MonsterPlant("Venulax", 10, 10, 10, 10);
		MonsterPlant golbasaur = new MonsterPlant("Golbasaur", 10, 10, 10, 10);
		MonsterPlant caternite = new MonsterPlant("Caternite", 10, 10, 10, 10);
		MonsterPlant metadrill = new MonsterPlant("Metadrill", 10, 10, 10, 10);
		MonsterPlant metadrio = new MonsterPlant("Metadrio", 10, 10, 10, 10);

		MonsterFire moltnx = new MonsterFire("Moltnx", 10, 10, 10, 10);
		MonsterFire charas = new MonsterFire("Charas", 10, 10, 10, 10);
		MonsterFire tangizard = new MonsterFire("Tangizard", 10, 10, 10, 10);
		MonsterFire beemeleon = new MonsterFire("Beemeleon", 10, 10, 10, 10);
		MonsterFire growther = new MonsterFire("Growther", 10, 10, 10, 10);
		MonsterFire moltvee = new MonsterFire("Moltvee", 10, 10, 10, 10);
		MonsterFire flaredon = new MonsterFire("Flaredon", 10, 10, 10, 10);

		MonsterWater meowcruel = new MonsterWater("Meowcruel", 50, 50, 50, 50);
		MonsterWater seesect = new MonsterWater("Seesect", 50, 50, 50, 50);
		MonsterWater mutoise = new MonsterWater("Mutoise", 50, 50, 50, 50);
		MonsterWater omachamp = new MonsterWater("Omachamp", 50, 50, 50, 50);
		MonsterWater tauras = new MonsterWater("Tauras", 50, 50, 50, 50);
		MonsterWater dewpuff = new MonsterWater("Dewpuff", 50, 50, 50, 50);

		bdMonter.add(catercute);
		bdMonter.add(weepinutor);
		bdMonter.add(venulax);
		bdMonter.add(golbasaur);
		bdMonter.add(caternite);
		bdMonter.add(metadrill);
		bdMonter.add(metadrio);

		bdMonter.add(moltnx);
		bdMonter.add(charas);
		bdMonter.add(tangizard);
		bdMonter.add(beemeleon);
		bdMonter.add(growther);
		bdMonter.add(moltvee);
		bdMonter.add(flaredon);

		bdMonter.add(meowcruel);
		bdMonter.add(seesect);
		bdMonter.add(mutoise);
		bdMonter.add(omachamp);
		bdMonter.add(tauras);
		bdMonter.add(dewpuff);
		
		createAllMonsters(bdMonter);

		Move ascuas = new Move("Ascuas", 60);
		Move lluevehojas = new Move("LlueveHojas", 120);
		Move pistolaAgua = new Move("Pistola Agua", 55);
		Move ataqueRapido = new Move("Ataque rapido", 50);
		Move placaje = new Move("Placaje", 50);
		Move araniazo = new Move("arañazo", 35);

		bdMove.add(ascuas);
		bdMove.add(lluevehojas);
		bdMove.add(pistolaAgua);
		bdMove.add(ataqueRapido);
		bdMove.add(placaje);
		bdMove.add(araniazo);

		createAllMoves(bdMove);

		createMM(catercute.getName(), lluevehojas.getName());
		createMM(catercute.getName(), placaje.getName());
		
		System.out.println(selectMonster(seesect.getName()));
		
		Enemy jovenChano = new Enemy("Joven Chano", "Te desafio!!");
		Enemy mielLopez = new Enemy("Miel Lopez", "Muereee :)");
		Enemy beret = new Enemy("Beret", "Una cancion para llorar?");
		
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		
		monsters.add(dewpuff);
		monsters.add(tauras);
		
		ArrayList<Monster> monsters1 = new ArrayList<Monster>();
		
		monsters1.add(catercute);
		monsters1.add(moltnx);
		
		ArrayList<Monster> monsters2 = new ArrayList<Monster>();
		
		monsters2.add(venulax);
		monsters2.add(flaredon);
		
		LevelGame primero = new LevelGame("Primer Nivel", jovenChano, monsters, 0);
		LevelGame segundo = new LevelGame("Segundo Nivel", mielLopez, monsters1, 1);
		LevelGame tercero = new LevelGame("Tercero Nivel", beret, monsters2, 2);
		
		createEnemy(jovenChano);
		createEnemy(mielLopez);
		createEnemy(beret);
		
		createLevel(primero);
		createLevel(segundo);
		createLevel(tercero);
		

		
		closeBD();

	}

}
