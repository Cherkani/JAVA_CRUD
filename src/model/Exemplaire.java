package model;

public class Exemplaire {
	private int id;
	private String etat;
	private int id_livre;
	
	public Exemplaire(int id,String etat,int id_livre)
	{
		this.id = id;
		this.etat = etat;
		this.id_livre = id_livre;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getid_livre() {
		return id_livre;
	}

	public void setid_livre(int id_livre) {
		this.id_livre = id_livre;
	}
	
	


}
