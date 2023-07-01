package model;

public class Livre {

	private String titre ;
	private String auteur;
	private int nP;
	private int nE;
	
	
	public Livre(String titre, String auteur, int nP) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.nP = nP;
	}

	public String toString() {
		return titre;
	}
	
	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public int getnP() {
		return nP;
	}


	public void setnP(int nP) {
		this.nP = nP;
	}




	public int getnE() {
		return nE;
	}


	public void setnE(int nE) {
		this.nE = nE;
	}
	
	
	
}	

