package model;

public class Utilisateur {
	private int num;
	private String nom;
	private String mdp;
	private int role;
	private String filiere;
	private String dep;
    private String username;
    private String grade;
    private String adresse;


	public Utilisateur(String nom, String mdp,int role,String filiere,String dep,String adresse,String grade,String username)
	{
		this.nom = nom;
		this.mdp = mdp;
		this.role = role;
		this.filiere=filiere;
		this.dep=dep;
    this.adresse = adresse;
    this.grade = grade;
    this.username = username;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public String getFiliere() {
		return filiere;
	}


	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}


	public String getdep() {
		return dep;
	}


	public void setdep(String dep) {
		this.dep = dep;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

}
