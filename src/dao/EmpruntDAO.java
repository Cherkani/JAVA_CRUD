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

import connexion.Connexion;
import model.Emprunt;
import model.Exemplaire;
import model.Livre;
import model.Utilisateur;

public class EmpruntDAO {
	
	private Connexion myConn;
	private LivreDAO LivreDAO;
	DateFormat dateFormat;
	public int getNE(Livre livre) {
	    int nE = 0;
	    PreparedStatement myStmt = null;
	    ResultSet myRs = null;

	    try {
	        // Préparer la requête
	        myStmt = myConn.getConnexion().prepareStatement("SELECT nE FROM livre WHERE titre=? AND auteur=? AND nP=?");
	        myStmt.setString(1, livre.getTitre());
	        myStmt.setString(2, livre.getAuteur());
	        myStmt.setInt(3, livre.getnP());

	        // Exécuter la requête
	        myRs = myStmt.executeQuery();

	        // Récupérer le nE s'il existe un résultat
	        if (myRs.next()) {
	            nE = myRs.getInt("nE");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt, myRs);
	    }

	    return nE;
	}
	public EmpruntDAO(LivreDAO LivreDAO)
	{
		myConn = new Connexion();
		this.LivreDAO = LivreDAO;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public void modifier(Emprunt emprunt) {
		PreparedStatement myStmt = null;
		String sql = "update emprunt set date_debut = ? , date_retour = ? where id_exemplaire = ? and id_utilisateur = ?";
		
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, dateFormat.format(emprunt.getDate_debut()));
			myStmt.setString(2, dateFormat.format(emprunt.getDate_retour()));
			myStmt.setInt(3, emprunt.getid_exemplaire());
			myStmt.setInt(4,emprunt.getid_utilisateur());
			
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error updating an emprunt ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	public void ajouter(Emprunt emprunt) {
		
		PreparedStatement myStmt = null;
		String sql = "insert into emprunt values (?,?,?,?)";
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, dateFormat.format(emprunt.getDate_debut()));
			myStmt.setString(2, dateFormat.format(emprunt.getDate_retour()));
			myStmt.setInt(3, emprunt.getid_exemplaire());
			myStmt.setInt(4,emprunt.getid_utilisateur());
				
			// execute SQL
			
			int etatDajout = myStmt.executeUpdate();
			
		}catch (SQLException e) {
			//throw new DAOException("Error adding an emprunt ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	public List<Emprunt> getAll() {
		List<Emprunt> list = new ArrayList<Emprunt>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from emprunt");
			
			while (myRs.next()) {
				Emprunt tempEmprunt = convertRowToEmprunt(myRs);
				list.add(tempEmprunt);
			}
			return list;		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all emprunt... ", e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return list;
	}
	
	public List<Emprunt> userEmpruntes(int num)
	{
		List<Emprunt> list = new ArrayList<Emprunt>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			//System.out.println("titre: "+titre+"\t auteur: "+auteur);
			myStmt = myConn.getConnexion().prepareStatement("select date_debut, date_retour , id_exemplaire"
					+ "  id_utilisateur from emprunt join exemplaire on emprunt.id_exemplaire = exemplaire.id "
					+ "where id_utilisateur = ? and etat = \"reserve\"");
			
			myStmt.setInt(1, num);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Emprunt tempEmprunt = convertRowToEmprunt(myRs);
				list.add(tempEmprunt);
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		
		return list;
	}

	public Emprunt cherchereEmprunt(String beneficiaire,int idExemplaire)
	{
		Emprunt list = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.getConnexion().prepareStatement("select * from emprunt where id_utilisateur = ? and id_exemplaire = ? ");
			
			myStmt.setString(1, beneficiaire);
			myStmt.setInt(2, idExemplaire);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				 list = convertRowToEmprunt(myRs);
				
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			close(myStmt, myRs);
		}
		
		return list;
	}

	
	private Emprunt convertRowToEmprunt(ResultSet myRs) throws Exception
	{
		Emprunt tempEmprunt= null;
		
		try{
			int id_exemplaire = myRs.getInt("id_exemplaire");
			Date date_debut = myRs.getDate("date_debut");
			Date date_retour = myRs.getDate("date_retour");
			int id_utilisateur=myRs.getInt("id_exemplaire");

			
			tempEmprunt = new Emprunt(date_debut, date_retour, id_utilisateur, id_exemplaire);
		
		} catch (SQLException e) {
			//throw new DAOException("Error reading the ResultSet ... ", e);
			e.printStackTrace();
		}
						
		return tempEmprunt;
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
	
