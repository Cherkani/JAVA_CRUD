package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import model.Exemplaire;
import model.Livre;


public class ExemplaireDAO {
	private Connexion myConn = new Connexion();
	
	
	public void supprimer(int id) {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement("delete from exemplaire where id=?");
			
			// set param
			myStmt.setInt(1,id);
			
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error deleting an examplaire ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
	}
	
	public void modifier(Exemplaire exemplaire) {
		PreparedStatement myStmt = null;
		String sql = "update exemplaire set etat = ? , id_livre = ? where id = ? ";
		
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, exemplaire.getEtat());
			myStmt.setLong(2, exemplaire.getid_livre());
			myStmt.setInt(3, exemplaire.getId());
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error updating an examplaire ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	public void ajouter(Exemplaire exemplaire) {
		
		PreparedStatement myStmt = null;
		String sql = "insert into exemplaire values (null,?,?)";
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, exemplaire.getEtat());
			myStmt.setLong(2, exemplaire.getid_livre());
				
			// execute SQL
			
			int etatDajout = myStmt.executeUpdate();
			System.out.println("etat: "+etatDajout);
		}catch (SQLException e) {
			//throw new DAOException("Error adding an employee ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	public List<Exemplaire> disponibleExemplaires(int nE) {
	    List<Exemplaire> exempList = new ArrayList<>();

	    PreparedStatement myStmt = null;
	    ResultSet myRs = null;

	    try {
	        myStmt = myConn.getConnexion().prepareStatement("SELECT * FROM exemplaire WHERE etat = 'dispo' AND id_livre = ?");
	        myStmt.setInt(1, nE);
	        myRs = myStmt.executeQuery();

	        while (myRs.next()) {
	            Exemplaire tempExemplaire = convertRowToExemplaire(myRs);
	            exempList.add(tempExemplaire);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt, myRs);
	    }

	    return exempList;
	}
	
	public Exemplaire exemplaireParId(int idExemplaire)throws Exception {
		Exemplaire exemp = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = myConn.getConnexion().prepareStatement("select * from exemplaire where id = ?");
			
			myStmt.setInt(1, idExemplaire);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				exemp = convertRowToExemplaire(myRs);
			}
			return exemp;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Exemplaire convertRowToExemplaire(ResultSet myRs)
	{
		Exemplaire tempExemp= null;
		
		try{
			int id_livre = myRs.getInt("id_livre");
			String etat = myRs.getString("etat");
			int id = myRs.getInt("id");
			
			
			tempExemp = new Exemplaire(id, etat, id_livre);
		
		} catch (SQLException e) {
			//throw new DAOException("Error reading the ResultSet ... ", e);
			e.printStackTrace();
		}
						
		return tempExemp;
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
	

