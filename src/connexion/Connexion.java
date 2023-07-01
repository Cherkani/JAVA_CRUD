package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connexion {
	
	private Connection connexion;
	
	public Connexion() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/javajava";
		String username = "root";
		String password = "";
		try {
			Class.forName(driver);
			connexion = DriverManager.getConnection(url, username, password);
			System.out.println("DB connection successful to: " + url);
		} catch (SQLException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Make sure to run Mysql", "Database Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnexion()
	{
		return this.connexion;
		
		
	}
	public static void main(String []args) {
		Connexion a=new Connexion();
	}

}



