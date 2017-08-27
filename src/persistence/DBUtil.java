package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{

	private static DBUtil db;
	private static Connection con;

	private DBUtil (){
		try { 
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;"
					+ "DatabaseName=desafio;namedPipe=true","elton","Elton@1997");
			System.out.println("Conexao ok");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static synchronized DBUtil getInstance(){
		if(db == null){
			db = new DBUtil();
		}
		return db;
	}
	public Connection getConnection() {
		return con; 
	}

	public void fechaConexao(){
		try {
			if(con!=null) con.close();
			con =null;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBUtil db = DBUtil.getInstance();
		Connection con = db.getConnection();
	}
}

