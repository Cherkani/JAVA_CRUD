package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.util.Util;

import connexion.Connexion;
import model.Livre;
import model.Utilisateur;

public class UtilisateurDAO {

	private Connexion myConn = new Connexion();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public UtilisateurDAO() {}
	
	public void supprimer(int num) {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement("delete from utilisateur where num=?");
			
			// set param
			myStmt.setInt(1,num);
			
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error deleting a user ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
	}
	
	
	

	
	public void ajouterEtudiant(Utilisateur etudiant) {
		
		PreparedStatement myStmt = null;
		String sql = "insert into utilisateur (nom,username,mdp,adresse,filiere,grade,dep,role) values (?,?,?,?,?,0,0,1)";
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, etudiant.getNom());
			myStmt.setString(2, etudiant.getUsername());
			myStmt.setString(3, etudiant.getMdp());
			myStmt.setString(4, etudiant.getAdresse());
			myStmt.setString(5, etudiant.getFiliere());
		
				
			// execute SQL
			
			int etatDajout = myStmt.executeUpdate();
			
		}catch (SQLException e) {
			//throw new DAOException("Error adding an employee ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
public void ajouterEnseignant(Utilisateur etudiant) {
		
		PreparedStatement myStmt = null;
		String sql = "insert into utilisateur (nom,username,mdp,adresse,filiere,grade,dep,role) values (?,?,?,0,0,?,?,2)";
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, etudiant.getNom());
			myStmt.setString(2, etudiant.getUsername());
			myStmt.setString(3, etudiant.getMdp());
			myStmt.setString(4, etudiant.getGrade());
			myStmt.setString(5, etudiant.getdep());
		
				
			// execute SQL
			
			int etatDajout = myStmt.executeUpdate();
			
		}catch (SQLException e) {
			//throw new DAOException("Error adding an employee ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}	
	
	public List<Utilisateur> getAllEtudiant() {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from utilisateur where role=1 order by nom");
			while (myRs.next()) {
				Utilisateur tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}

			return list;		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all employees ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return list;
	}
	
	public List<Utilisateur> getAllEnseignant() {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from utilisateur where role=2 order by nom");
			while (myRs.next()) {
				Utilisateur tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}

			return list;		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all employees ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return list;
	}

	


	public Utilisateur chercheParUsername(String username) {

		List<Utilisateur> list = new ArrayList<Utilisateur>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from utilisateur where username = \""+username+"\"");
			while (myRs.next()) {
				Utilisateur tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			if(list.size() == 1)
				return list.get(0);		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all users ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return null;

    }

	
	public Utilisateur chercheParNum( int num) throws SQLException {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from utilisateur where num = \""+num+"\"");
			while (myRs.next()) {
				Utilisateur tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			if(list.size() == 1)
				return list.get(0);		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all users... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return null;
    }
	
	public int getNUM(Utilisateur student) {
	    int num = 0;
	    PreparedStatement myStmt = null;
	    ResultSet myRs = null;

	    try {
	        // Préparer la requête
	        myStmt = myConn.getConnexion().prepareStatement("SELECT num FROM utilisateur WHERE nom=? AND username=? AND mdp=? AND role=1 ");
	        myStmt.setString(1,student.getNom());
	        myStmt.setString(2, student.getUsername());
	        myStmt.setString(3, student.getMdp());

	        // Exécuter la requête
	        myRs = myStmt.executeQuery();

	        // Récupérer le nE s'il existe un résultat
	        if (myRs.next()) {
	            num = myRs.getInt("num");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt, myRs);
	    }

	    return num;
	}
	
	public List<Utilisateur> chercheUtilisateur(String keyword)throws Exception {
		List<Utilisateur> list = new ArrayList<Utilisateur>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			if(keyword.length() > 0)
				keyword = "%"+keyword+"%";
			myStmt = myConn.getConnexion().prepareStatement("select * from utilisateur where nom like ? OR username like ?  ");
			
			myStmt.setString(1, keyword);
			myStmt.setString(2, keyword);
			;
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Utilisateur tempUtilisateur = convertRowToUser(myRs);
				list.add(tempUtilisateur);
			}
			return list;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public void updateEtudiant(int num,Utilisateur student) {
	    PreparedStatement myStmt = null;

	    try {
	        // Prepare the update statement
	        myStmt = myConn.getConnexion().prepareStatement("UPDATE utilisateur SET nom=?, username=?, mdp=?,adresse=? ,filiere=? WHERE num=?");
	        myStmt.setString(1, student.getNom());
	        myStmt.setString(2, student.getUsername());
	        myStmt.setString(3, student.getMdp());
	        myStmt.setString(4, student.getAdresse());
	        myStmt.setString(5, student.getFiliere());
	        myStmt.setInt(6, num);// Use the getNE method to retrieve the nE
	        System.out.println("Suppression de l'enregistrement avec l'identifiant " +num);
	        // Execute the update statement
	        myStmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt);
	    }
	}
	
	public void updateEnseignant(int num,Utilisateur student) {
	    PreparedStatement myStmt = null;

	    try {
	        // Prepare the update statement
	        myStmt = myConn.getConnexion().prepareStatement("UPDATE utilisateur SET nom=?, username=?, mdp=?,grade=? ,dep=? WHERE num=?");
	        myStmt.setString(1, student.getNom());
	        myStmt.setString(2, student.getUsername());
	        myStmt.setString(3, student.getMdp());
	        myStmt.setString(4, student.getGrade());
	        myStmt.setString(5, student.getdep());
	        myStmt.setInt(6, num);// Use the getNE method to retrieve the nE
	        System.out.println("Suppression de l'enregistrement avec l'identifiant " +num);
	        // Execute the update statement
	        myStmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt);
	    }
	}


	private Utilisateur convertRowToUser(ResultSet myRs) {
		
		Utilisateur tempUser= null;
		
		try{
			int num = myRs.getInt("num");
			String nom = myRs.getString("nom");
			String username = myRs.getString("username");
			String mdp = myRs.getString("mdp");
			String filiere = myRs.getString("filiere");
			String adresse = myRs.getString("adresse");
			String grade = myRs.getString("grade");
			String dep = myRs.getString("dep");
			byte role = myRs.getByte("role");

			tempUser = new Utilisateur(nom,  mdp, role, filiere,dep, adresse,grade, username);
		
		} catch (SQLException e) {
			//throw new DAOException("Error reading the ResultSet ... ", e);
		}
						
		return tempUser;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		if (myRs != null) {
			try {
				myRs.close();
			} catch (SQLException e) {
				//throw new DAOException("Error closing the ResultSet ... ", e);
			}
		}

		if (myStmt != null) {
			try {
				myStmt.close();
			} catch (SQLException e) {
				//throw new DAOException("Error closing the Statement ... ", e);
			}
		}
		
		if (myConn != null) {
			try {
				myConn.close();
			} catch (SQLException e) {
				//throw new DAOException("Error closing the Connection ... ", e);
			}
		}
	}

	private void close(Statement myStmt, ResultSet myRs) {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt)  {
		close(null, myStmt, null);		
	}
	

}


