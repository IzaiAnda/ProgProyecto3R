import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
	
	static Connection c = null;
	static Statement stmt = null;

	private static Logger logger = Logger.getLogger( "LoggerBD" );

	static {
		try {
			logger.setLevel( Level.FINEST);
			logger.addHandler( new FileHandler( "LoggerBD.xml") );
		} catch (Exception e) {}
	}
	public static void startBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Deusmon.db");
			c.setAutoCommit(false);
			logger.log(Level.FINER,"Opened database successfully");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void insert (String tName, String code) {

		try {
			startBD();

			stmt = c.createStatement();
			String sql = "INSERT INTO "+tName+
					" VALUES "+ code+";"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			logger.log(Level.SEVERE,  e.getClass().getName() + ": " + e.getMessage() );
			System.out.println(e);
			System.exit(0);
		}
		logger.log(Level.FINER, "Records created successfully");

	}
	
	public void create (String tName, String code) {
		try { 
			startBD();
			stmt = c.createStatement();
			String sql = "CREATE TABLE "+tName +
					"("+code+");"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			logger.log(Level.SEVERE,  e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	public void create () {
		try { 
			startBD();
			stmt = c.createStatement();
			String sql = "CREATE TABLE PLAYER ( NAME_P VARCHAR(30) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(30) NOT NULL);";
			//String sql = "CREATE TABLE MONSTER ( NAME_M VARCHAR(30) NOT NULL PRIMARY KEY,TYPE VARCHAR(30) NOT NULL, HP INTEGER NOT NULL, ATK INTEGER NOT NULL, DEF INTEGER NOT NULL, VEL INTEGER NOT NULL);";
			//String sql = "CREATE TABLE MOVE ( NAME_MOV VARCHAR(30) NOT NULL PRIMARY KEY,TYPE VARCHAR(30) NOT NULL, DMG INTEGER NOT NULL, DESCRIPTION VARCHAR(30), PERCENT INTEGER);";
			//String sql = "CREATE TABLE MM ( NAME_M VARCHAR(30) NOT NULL PRIMARY KEY,NAME_MOV VARCHAR(30) NOT NULL);";
			//String sql = "CREATE TABLE LEVEL ( NAME_L VARCHAR(30) NOT NULL PRIMARY KEY,TXT VARCHAR(30) NOT NULL);";
			//String sql = "CREATE TABLE LM ( NAME_L VARCHAR(30) NOT NULL PRIMARY KEY,NAME_M VARCHAR(30) NOT NULL, PI INTEGER NOT NULL);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			logger.log(Level.SEVERE,  e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	public static void main(String[] args) {
		BD b= new BD();
		
		//b.create();
		
		b.insert("PLAYER(NAME_P, PASSWORD)", "('IZAI','123')");
		
	}
	
}
