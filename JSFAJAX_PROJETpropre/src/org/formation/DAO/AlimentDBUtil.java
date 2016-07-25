package org.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.formation.model.Aliment;

//Singleton pour la connexion à la base de données
public class AlimentDBUtil {

	// Attributs:
	private static AlimentDBUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/aliment_h2";// (java:comp/env)
																// --> le
																// serveur doit
																// se charger de
																// retrouver la
																// dataSource ||
																// (jdbc/aliment_h2)
																// --> url de la
																// data source
																// definit dans
																// le
																// context.xml
																// ou web.xml

	/**
	 * -----------1ere Partie:Singleton: connexion unique--------------
	 */

	// instance unique
	public static AlimentDBUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new AlimentDBUtil();
		}
		return instance;

	}
	// Constructeur privé

	private AlimentDBUtil() throws Exception {
		dataSource = getDataSource();// rien à voir avec la partie singleton
										// mais necessaire pour la 3 eme partie.
										// recuperation de la data source à la
										// création de la connexion.
	};

	/**
	 * -----------2eme Partie: Creation de la Data Source--------------
	 */

	public DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();

		DataSource theDataSource = (DataSource) context.lookup(jndiName);

		return theDataSource;
	}

	/**
	 * -----------3eme Partie: méthode n°1 pour récupérer la liste des éléments
	 * de la base de données. --------------
	 */

	public List<Aliment> getAliments() throws Exception {
		List<Aliment> listAliments = new ArrayList<>();

		// partie récupérer du TestServlet:

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from aliment;";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int id = myRs.getInt("id");
				String nom = myRs.getString("nom");
				String categorie = myRs.getString("categorie");
				String couleur = myRs.getString("couleur");
				listAliments.add(new Aliment(id, nom, categorie, couleur));
			}
		} finally {
			close(myConn, myStmt);
		}
		// -----------------------------------------

		return listAliments;

	}

	/**
	 * -----------3eme Partie: méthode n°2 pour récupérer un élément de la base
	 * de données. --------------
	 */

	public Aliment getAliment(int monId) throws Exception {
		Aliment aliment = new Aliment();
		aliment.setId(monId);

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from aliment where id =" + monId + ";";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				aliment.setNom(myRs.getString("nom"));
				aliment.setCategorie(myRs.getString("categorie"));
				aliment.setCouleur(myRs.getString("couleur"));
			}
		} finally {
			close(myConn, myStmt);
		}
		// -----------------------------------------

		return aliment;

	}

	/**
	 * -----------3eme Partie: méthode n°2 pour ajouter un Aliment
	 * --------------
	 */

	public void addAliment(Aliment aliment) throws Exception {

		// partie récupérer du TestServlet:

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "insert into aliment (nom, categorie,couleur) values(?,?,?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, aliment.getNom());
			myStmt.setString(2, aliment.getCategorie());
			myStmt.setString(3, aliment.getCouleur());

			myStmt.execute();

		} finally {
			close(myConn, myStmt);
		}
		// -----------------------------------------

	}

	/**
	 * -----------3eme Partie: méthode n°3 pour modifier un Aliment
	 * --------------
	 */

	public void updateAliment(Aliment aliment) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		/*Aliment aliment = this.getAliment(monId);
		
		if (nom == null ||  nom.equals("")){
			nom = aliment.getNom();
		}
		
		if (categorie == null ||  categorie.equals("")){
			categorie = aliment.getCategorie();
		}
		
		
		if (couleur == null ||  couleur.equals("")){
			couleur = aliment.getCouleur();
		}	
		*/


		try {
			myConn = dataSource.getConnection();

			/*String sql = "update aliment set (nom, categorie,couleur) values(?,?,?) where id = " + monId;*/
			
			String sql = "update aliment set nom=?, categorie=?,couleur=?  where id = " + aliment.getId();
			
			myStmt = myConn.prepareStatement(sql);

			/*myStmt.setString(1, nom);
			myStmt.setString(2, categorie);
			myStmt.setString(3, couleur);*/
			
			myStmt.setString(1, aliment.getNom());
			myStmt.setString(2, aliment.getCategorie());
			myStmt.setString(3, aliment.getCouleur());

			myStmt.execute();

		} finally {
			close(myConn, myStmt);
		}
		// -----------------------------------------

	}

	/**
	 * -----------3eme Partie: méthode n°4 pour supprimer un Aliment
	 * --------------
	 */

	public void delAliment(int monId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		Aliment aliment = this.getAliment(monId);
		
		try {
			myConn = dataSource.getConnection();

			String sql = "delete from aliment where id =" + monId;

			myStmt = myConn.prepareStatement(sql);
			myStmt.execute();

		} finally {
			close(myConn, myStmt);
		}
		// -----------------------------------------

	}

	/**
	 * -----------4eme Partie: méthode pour la déconnexion. --------------
	 */
	private void close(Connection myConn, Statement myStmt) throws Exception {
		// TODO Auto-generated method stub
		if (myStmt != null) {
			myStmt.close();
		}
		if (myConn != null) {
			myConn.close();
		}

	}
}
