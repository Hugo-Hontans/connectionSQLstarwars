package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;




public class Noms {
	
	private Connection connexion;

	public Noms() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Utilisateur> recupererUtilisateurs(){
		
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Statement statement = null;
		ResultSet resultat = null;
		
		loadDatabase();
		
		try {
			statement = connexion.createStatement();
			
			//execution de la requete
			resultat = statement.executeQuery("SELECT name, surname FROM jedi_masters;");
			
			//recuperer les donnees
			while (resultat.next()) {
				String nom = resultat.getString("name");
				String prenom = resultat.getString("surname");
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {		
		} finally {
			try {
				if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
			} catch (SQLException ignore) {			
			}
		}
		
		return utilisateurs;
	
	}
	
	 private void loadDatabase() {
	        // Chargement du driver
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	        }

	        try {
	            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/starwars", "root", "motdepasse");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
