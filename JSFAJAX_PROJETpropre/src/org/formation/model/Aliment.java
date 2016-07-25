package org.formation.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Aliment {

	// Attributs:
	private int id;
	private String nom;
	private String categorie;
	private String couleur;

	// Getters et setters:

	public String getNom() {
		return nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	// constructeurs:

	public Aliment(int id, String nom, String categorie, String couleur) {
		super();
		this.id = id;
		this.nom = nom;
		this.categorie = categorie;
		this.couleur = couleur;
	}

	public Aliment() {
		super();
	}

}
