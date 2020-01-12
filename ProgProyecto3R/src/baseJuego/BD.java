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
import level.Player;
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
	}

	// LA IDEA ES QUE AYA UN INT EN TU PERSONAJE QUE TE DIGA QUE NIVEL ERES, DEPENDE
	// DE TU NIVEL CARGAS LOS V INELES ANTERIORES.
	public static void create() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE PLAYER ( NAME_P VARCHAR(30) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(30) NOT NULL, LEVEL_P INTEGER NOT NULL);";
			String sql1 = "CREATE TABLE MONSTER ( NAME_M VARCHAR(30) NOT NULL PRIMARY KEY,TYPE VARCHAR(30) NOT NULL, HP INTEGER NOT NULL, ATK INTEGER NOT NULL, DEF INTEGER NOT NULL, VEL INTEGER NOT NULL);";
			String sql2 = "CREATE TABLE MOVE ( NAME_MOV VARCHAR(30) NOT NULL PRIMARY KEY,DMG INTEGER NOT NULL);";
			String sql3 = "CREATE TABLE MM ( NAME_M VARCHAR(30) NOT NULL, NAME_MOV VARCHAR(30) NOT NULL, PRIMARY KEY (NAME_M, NAME_MOV), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_MOV) REFERENCES MOVE(NAME_MOV));";
			String sql4 = "CREATE TABLE ENEMY ( NAME_E VARCHAR(30) NOT NULL PRIMARY KEY,TXT VARCHAR(30) NOT NULL);";
			String sql5 = "CREATE TABLE LEVEL ( NAME_L VARCHAR(30) NOT NULL, NAME_E VARCHAR(30) NOT NULL,NAME_M VARCHAR(30) NOT NULL,DIFFICULTY INTEGER NOT NULL, ENEMY INTEGER NOT NULL,PRIMARY KEY (NAME_L,NAME_E,NAME_M,ENEMY), FOREIGN KEY (NAME_M) REFERENCES MONSTER(NAME_M),FOREIGN KEY (NAME_E) REFERENCES ENEMY(NAME_E));";
			// LEVEL.ENEMY es un in 0/1 para decir si es o no el mounstruo del enemigo 1:si
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());

		}
		return i;
	}

	public static Player selectJugadorClass(String nom) {
		Player i = new Player(nom, 0);
		try {
			stmt2 = c.prepareStatement("SELECT LEVEL_P FROM PLAYER WHERE NAME_P=?");
			stmt2.setString(1, nom);
			ResultSet rs = stmt2.executeQuery();
			i.setLevel(rs.getInt(1));
			stmt2.close();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());

		}
		return i;
	}

	public static void updateJugador(Player p) {

		try {
			stmt2 = c.prepareStatement("UPDATE PLAYER SET LEVEL_P = ? WHERE NAME_P = ?");
			stmt2.setInt(1, p.getLevel());
			stmt2.setString(2, p.getName());
			stmt2.executeUpdate();
			stmt2.close();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());

		}
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			stmt2.setInt(2, move.getDamage());

			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void createMM(Monster mon, Move mov) {
		try {

			stmt2 = c.prepareStatement("INSERT INTO MM (NAME_M,NAME_MOV) VALUES (?, ?)");
			stmt2.setString(1, mon.getName());
			stmt2.setString(2, mov.getName());
			stmt2.executeUpdate();
			stmt2.close();
			c.commit();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());

		}
		return s;
	}

	public static LinkedList<Move> selectMM(Monster mon) {
		LinkedList<String> names = new LinkedList<>();
		LinkedList<Move> s = new LinkedList<>();
		try {
			stmt2 = c.prepareStatement("SELECT NAME_M,NAME_MOV FROM MM WHERE NAME_M=?");
			stmt2.setString(1, mon.getName());
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				names.add(new String(rs.getString(2)));
			}

			stmt2.close();

			for (int i = 0; i < names.size(); i++) {
				s = selectMove(names.get(i), s);
			}

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());

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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
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
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static Enemy selectEnemy(String name) {
		Enemy s = new Enemy();
		try {

			stmt2 = c.prepareStatement("SELECT NAME_E,TXT FROM ENEMY WHERE NAME_E=?");
			stmt2.setString(1, name);
			ResultSet rs = stmt2.executeQuery();

			s = new Enemy(rs.getString(1), rs.getString(2));
			stmt2.close();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}

		return s;
	}

	public static void createLevel(LevelGame level) {
		try {

			Iterator it = level.getMonsters().entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pair = (Map.Entry) it.next();

				Monster m = (Monster) pair.getKey();

				stmt2 = c.prepareStatement(
						"INSERT INTO LEVEL (NAME_L,NAME_E,NAME_M,DIFFICULTY,ENEMY) VALUES (?, ?, ?, ?, ?)");
				stmt2.setString(1, level.getName());
				stmt2.setString(2, level.getEnemy().getName());
				stmt2.setString(3, m.getName());
				stmt2.setInt(4, level.getDifficulty());
				stmt2.setInt(5, 0);
				stmt2.executeUpdate();
				stmt2.close();
				c.commit();

				it.remove(); // avoids a ConcurrentModificationException
			}

			Iterator it2 = level.getMonstersEnemy().entrySet().iterator();

			while (it2.hasNext()) {

				Map.Entry pair = (Map.Entry) it2.next();

				Monster m = (Monster) pair.getKey();

				stmt2 = c.prepareStatement(
						"INSERT INTO LEVEL (NAME_L,NAME_E,NAME_M,DIFFICULTY,ENEMY) VALUES (?, ?, ?, ?, ?)");
				stmt2.setString(1, level.getName());
				stmt2.setString(2, level.getEnemy().getName());
				stmt2.setString(3, m.getName());
				stmt2.setInt(4, level.getDifficulty());
				stmt2.setInt(5, 1);
				stmt2.executeUpdate();
				stmt2.close();
				c.commit();

				it2.remove(); // avoids a ConcurrentModificationException
			}

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static LinkedList<LevelGame> selectAllLevelsUnder(int level) {
		LinkedList<LevelGame> s = new LinkedList<>();
		HashMap<String, LevelGame> training = new HashMap<>();
		try {

			stmt2 = c.prepareStatement("SELECT * FROM LEVEL WHERE DIFFICULTY<=?");
			stmt2.setInt(1, level + 1);
			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {
				if (!training.containsKey(rs.getObject(1))) {
					training.put(rs.getString(1),
							new LevelGame(rs.getString(1), selectEnemy(rs.getString(2)),
									new HashMap<Monster, LinkedList<Move>>(), new HashMap<Monster, LinkedList<Move>>(),
									rs.getInt(4)));
				}
				if (rs.getInt(5) == 0) {
					training.get(rs.getString(1)).addMonsters(selectMonster(rs.getString(3)),
							selectMM(rs.getString(3)));
				} else {
					training.get(rs.getString(1)).addMonstersEnemy(selectMonster(rs.getString(3)),
							selectMM(rs.getString(3)));
				}
			}

			stmt2.close();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}

		Iterator it = training.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			s.add((LevelGame) pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}

		return s;
	}

	public static LevelGame selectLevel(String level) {
		LevelGame r = null;

		try {

			stmt2 = c.prepareStatement("SELECT * FROM LEVEL WHERE NAME_L<=?");
			stmt2.setString(1, level);
			ResultSet rs = stmt2.executeQuery();

			r = new LevelGame(level, selectEnemy(rs.getString(2)), new HashMap<Monster, LinkedList<Move>>(),
					new HashMap<Monster, LinkedList<Move>>(), rs.getInt(4));

			while (rs.next()) {

				if (rs.getInt(5) == 0) {
					r.addMonsters(selectMonster(rs.getString(3)), selectMM(rs.getString(3)));
				} else {
					r.addMonstersEnemy(selectMonster(rs.getString(3)), selectMM(rs.getString(3)));
				}

			}

			stmt2.close();

		} catch (Exception e) {
			logger.log(Level.FINE,e.getClass().getName() + ": " + e.getMessage());
		}

		return r;
	}

	public static void main(String[] args) throws SQLException {
		startBD();

		create();

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
		Move lanzallamas = new Move("Lanzallamas", 100);
		Move giroFuego = new Move("Giro fuego", 45);
		Move patadaIgnea = new Move("Patada ignea", 50);
		Move ruedaFuego = new Move("Rueda fuego", 50);
		Move fuegoFatuo = new Move("Fuego fatuo", 30);

		Move lluevehojas = new Move("LlueveHojas", 120);
		Move hojaAguda = new Move("Hoja aguda", 55);
		Move latigoCepa = new Move("Latigo cepa", 45);
		Move hojaAfilada = new Move("Hoja afilada", 60);
		Move energiBola = new Move("EnergiBola", 65);
		Move rayoSolar = new Move("Rayo solar", 100);

		Move pistolaAgua = new Move("Pistola Agua", 60);
		Move burbuja = new Move("Burbuja", 45);
		Move cascada = new Move("Cascada", 55);
		Move surf = new Move("Surf", 60);
		Move remolino = new Move("Remolino", 40);
		Move rayoBurbuja = new Move("Rayo burbuja", 55);
		Move hidrobomba = new Move("Hidrobomba", 100);

		Move picotazo = new Move("Picotazo", 60);
		Move golpe = new Move("Golpe", 45);
		Move ataqueRapido = new Move("Ataque rapido", 50);
		Move placaje = new Move("Placaje", 50);
		Move araniazo = new Move("Arañazo", 35);

		bdMove.add(ascuas);
		bdMove.add(lanzallamas);
		bdMove.add(giroFuego);
		bdMove.add(patadaIgnea);
		bdMove.add(ruedaFuego);
		bdMove.add(fuegoFatuo);

		bdMove.add(lluevehojas);
		bdMove.add(hojaAguda);
		bdMove.add(latigoCepa);
		bdMove.add(hojaAfilada);
		bdMove.add(energiBola);
		bdMove.add(rayoSolar);

		bdMove.add(pistolaAgua);
		bdMove.add(burbuja);
		bdMove.add(cascada);
		bdMove.add(surf);
		bdMove.add(remolino);
		bdMove.add(rayoBurbuja);
		bdMove.add(hidrobomba);

		bdMove.add(picotazo);
		bdMove.add(golpe);
		bdMove.add(ataqueRapido);
		bdMove.add(placaje);
		bdMove.add(araniazo);
		createAllMoves(bdMove);

		createMM(catercute, lluevehojas);
		createMM(catercute, ataqueRapido);
		createMM(catercute, placaje);
		createMM(catercute, araniazo);

		createMM(weepinutor, picotazo);
		createMM(weepinutor, placaje);
		createMM(weepinutor, hojaAfilada);
		createMM(weepinutor, rayoSolar);

		createMM(venulax, picotazo);
		createMM(venulax, placaje);
		createMM(venulax, hojaAfilada);
		createMM(venulax, rayoSolar);

		createMM(golbasaur, picotazo);
		createMM(golbasaur, placaje);
		createMM(golbasaur, hojaAfilada);
		createMM(golbasaur, rayoSolar);

		createMM(moltnx, ascuas);
		createMM(moltnx, ataqueRapido);
		createMM(moltnx, placaje);
		createMM(moltnx, araniazo);

		createMM(flaredon, ascuas);
		createMM(flaredon, lanzallamas);
		createMM(flaredon, giroFuego);
		createMM(flaredon, araniazo);

		Enemy jovenChano = new Enemy("Joven Chano", "Te desafio!!");
		createEnemy(jovenChano);

		LevelGame level1 = new LevelGame("Pueblo Paleta", jovenChano, new HashMap<Monster, LinkedList<Move>>(),
				new HashMap<Monster, LinkedList<Move>>(), 0);
		level1.addMonsters(catercute, selectMM(catercute));
		level1.addMonsters(golbasaur, selectMM(golbasaur));

		level1.addMonstersEnemy(moltnx, selectMM(moltnx));
		level1.addMonstersEnemy(flaredon, selectMM(flaredon));

		createLevel(level1);

		System.out.println(selectLevel(level1.getName()));

		Enemy domingueroPaco = new Enemy("Dominguero Paco", "Jojojo, no vencerás a mis plantas!!");
		createEnemy(domingueroPaco);

		LevelGame level2 = new LevelGame("Ciudad Olivo", domingueroPaco, new HashMap<Monster, LinkedList<Move>>(),
				new HashMap<Monster, LinkedList<Move>>(), 0);
		level2.addMonsters(weepinutor, selectMM(weepinutor));
		level2.addMonsters(moltnx, selectMM(moltnx));
		level2.addMonsters(flaredon, selectMM(flaredon));

		level2.addMonstersEnemy(golbasaur, selectMM(golbasaur));
		level2.addMonstersEnemy(weepinutor, selectMM(weepinutor));
		level2.addMonstersEnemy(catercute, selectMM(catercute));

		createLevel(level2);

		System.out.println(selectLevel(level2.getName()));
		
		createJugador("a", "a");

		
		createMM(tangizard,ataqueRapido);
		createMM(tangizard,giroFuego);
		createMM(tangizard,patadaIgnea);
		createMM(tangizard,rayoSolar);
		
		createMM(omachamp,placaje);
		createMM(omachamp,ataqueRapido);
		createMM(omachamp,araniazo);
		createMM(omachamp,latigoCepa);
		
		createMM(mutoise,picotazo);
		createMM(mutoise,lluevehojas);
		createMM(mutoise,araniazo);
		createMM(mutoise,lanzallamas);
		
		createMM(tauras,golpe);
		createMM(tauras,remolino);
		createMM(tauras,surf);
		createMM(tauras,placaje);
		
		createMM(dewpuff,placaje);
		createMM(dewpuff,ascuas);
		createMM(dewpuff,lanzallamas);
		createMM(dewpuff,araniazo);
		
		
		createMM(meowcruel,hidrobomba);
		createMM(meowcruel,surf);
		createMM(meowcruel,rayoBurbuja);
		createMM(meowcruel,latigoCepa);
		Enemy giovanni = new Enemy("Giovanni", "Mas quisieras vencerme!!");
		createEnemy(giovanni);

		LevelGame level3 = new LevelGame("Ciudad Roble", giovanni, new HashMap<Monster, LinkedList<Move>>(),
				new HashMap<Monster, LinkedList<Move>>(), 0);
		
		level3.addMonsters(tangizard, selectMM(tangizard));
		level3.addMonsters(omachamp, selectMM(omachamp));
		level3.addMonsters(meowcruel, selectMM(meowcruel));

		level3.addMonstersEnemy(mutoise, selectMM(mutoise));
		level3.addMonstersEnemy(tauras, selectMM(tauras));
		level3.addMonstersEnemy(dewpuff, selectMM(dewpuff));

		
		createLevel(level3);
		
		
		closeBD();

	}

}
