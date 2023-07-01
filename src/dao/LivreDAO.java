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

import connexion.Connexion;
import model.Emprunt;
import model.Livre;

public class LivreDAO {

	private Connexion myConn = new Connexion();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
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
	public void update(int nE,Livre livre) {
	    PreparedStatement myStmt = null;

	    try {
	        // Prepare the update statement
	        myStmt = myConn.getConnexion().prepareStatement("UPDATE livre SET titre=?, auteur=?, nP=? WHERE nE=?");
	        myStmt.setString(1, livre.getTitre());
	        myStmt.setString(2, livre.getAuteur());
	        myStmt.setInt(3, livre.getnP());
	        myStmt.setInt(4, nE);// Use the getNE method to retrieve the nE
	        System.out.println("Suppression de l'enregistrement avec l'identifiant " +nE);
	        // Execute the update statement
	        myStmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(myStmt);
	    }
	}


	public void supprimer(int nE) {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement("delete from livre where nE=?");
			System.out.println("Suppression de l'enregistrement avec l'identifiant " + nE);

			// set param
			myStmt.setInt(1,nE);
			
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error deleting a book ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
	}
	
	public void modifier(Livre livre) {
		PreparedStatement myStmt = null;
		String sql = "update livre set titre = ?  "
				+ "  auteur = ? ,  nP = ? where nE = ?";
		
		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, livre.getTitre());
			myStmt.setString(2, livre.getAuteur());
			myStmt.setInt(3, livre.getnP());
			myStmt.setInt(4, livre.getnE());
			
			// execute SQL
			myStmt.executeUpdate();			
		}catch (SQLException e) {
			//throw new DAOException("Error updating a book ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	public void ajouter(Livre livre) {
		
		PreparedStatement myStmt = null;
		String sql = "insert into livre (auteur, titre, np) values (?,?,?)";

		try {
			// prepare statement
			myStmt = myConn.getConnexion().prepareStatement(sql);
			
			// set params
			myStmt.setString(1, livre.getAuteur());
			myStmt.setString(2, livre.getTitre());
			myStmt.setInt(3, livre.getnP());
			
			
			
			
			// execute SQL
			
			int etatDajout = myStmt.executeUpdate();
			
		}catch (SQLException e) {
			//throw new DAOException("Error adding a book ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt);
		}
		
	}
	
	
	public int nbrDispoExemplaire(String nE)
	{
		Statement myStmt = null;
		ResultSet myRs = null;
		
		int nbr_des_exemplaire_dispo = -1;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select count(*) as disponible from exemplaire where "
					+ "id_livre = "+nE+" and etat = 'dispo'");
			
			while (myRs.next()) {
				nbr_des_exemplaire_dispo = myRs.getInt("disponible");
			}
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		return nbr_des_exemplaire_dispo;
	}
	
	
	public List<Livre> getAll() {
		List<Livre> list = new ArrayList<Livre>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.getConnexion().createStatement();
			myRs = myStmt.executeQuery("select * from livre");
			
			while (myRs.next()) {
				Livre tempLivres = convertRowToBook(myRs);
				list.add(tempLivres);
			}
			return list;		
		}catch (SQLException e) {
			//throw new DAOException("Error searching all books ... ", e);
			e.printStackTrace();
		}finally {
			close(myStmt, myRs);
		}
		return list;
	}
	
	public Livre livreParSonExemplaire(int id)throws Exception {
		Livre list = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.getConnexion().prepareStatement("select nE,titre"
					+ "  auteur , nP from livre join exemplaire "
					+ "on livre.nE = exemplaire.id_livre join emprunt "
					+ "on emprunt.id_exemplaire = exemplaire.id where id = ? ");
			
			myStmt.setInt(1, id);
			;
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				list = convertRowToBook(myRs);
			}
			return list;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Livre chercheLivre(long nE)throws Exception {
		

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Livre tempLivre = null;

		try {
			
			myStmt = myConn.getConnexion().prepareStatement("select * from livre where nE = ? ");
			
			
			myStmt.setLong(1, nE);
			;
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				 tempLivre = convertRowToBook(myRs);
			}
			return tempLivre;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}


	
	public List<Livre> chercheLivre(String keyword)throws Exception {
		List<Livre> list = new ArrayList<Livre>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			if(keyword.length() > 0)
				keyword = "%"+keyword+"%";
			myStmt = myConn.getConnexion().prepareStatement("select * from livre where titre like ? OR auteur like ? ");
			
			myStmt.setString(1, keyword);
			myStmt.setString(2, keyword);
			;
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Livre tempLivre = convertRowToBook(myRs);
				list.add(tempLivre);
			}
			return list;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Livre> chercheLivre(String titre,String auteur)throws Exception {
		List<Livre> list = new ArrayList<Livre>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			if(titre.length() > 0)
				titre = "%"+titre+"%";
			if(auteur.length() > 0)
				auteur = "%"+auteur+"%";
			myStmt = myConn.getConnexion().prepareStatement("select * from livre where (titre like ? and auteur like ? ) or (titre like ? or auteur like ? ) ");
			
			myStmt.setString(1, titre);
			myStmt.setString(2, auteur);
			myStmt.setString(3, titre);
			myStmt.setString(4, auteur);
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Livre tempLivre = convertRowToBook(myRs);
				list.add(tempLivre);
			}
			return list;
		}catch (SQLException e) {
			throw new Exception();
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private Livre convertRowToBook(ResultSet myRs) {
		
		Livre tempBook= null;
		
		try{
	
			String titre = myRs.getString("titre");
			String auteur = myRs.getString("auteur");
			int nP = myRs.getInt("nP");
			
			tempBook = new Livre(titre,auteur,nP);
		
		} catch (SQLException e) {
			//throw new DAOException("Error reading the ResultSet ... ", e);
		}
						
		return tempBook;
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


