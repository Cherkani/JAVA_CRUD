package model;

import java.util.Date;

public class Emprunt {
	private Date date_debut;
	private Date date_retour;
	private int id_utilisateur;
	private int id_exemplaire;
	
	
	public Emprunt(Date date_debut, Date date_retour, int id_utilisateur, int id_exemplaire) {
		super();
		this.date_debut = date_debut;
		this.date_retour = date_retour;
		this.id_utilisateur = id_utilisateur;
		this.id_exemplaire = id_exemplaire;
	}


	public Date getDate_debut() {
		return date_debut;
	}


	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}


	public Date getDate_retour() {
		return date_retour;
	}


	public void setDate_retour(Date date_retour) {
		this.date_retour = date_retour;
	}


	public int getid_utilisateur() {
		return id_utilisateur;
	}


	public void setid_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}


	public int getid_exemplaire() {
		return id_exemplaire;
	}


	public void setid_exemplaire(int id_exemplaire) {
		this.id_exemplaire = id_exemplaire;
	}
	
	
	
	
	
	
	
}
