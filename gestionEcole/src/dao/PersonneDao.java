package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Personne;

public class PersonneDao  extends GestionDao<Personne>{
	
	
	
	@Override
	public Personne find(Connection conn, Personne bean) {

		ResultSet result = null;
		Statement stmt = null;
		Personne personneFound = null;
		String requete = "select id, nom, prenom, type from personne where id  = "  + bean.getId() + " ;"  ;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(requete);
			if (result!=null && result.next())
			{
				personneFound = new Personne(result.getInt(1),result.getString(2),result.getString(3),result.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return personneFound;
	}	


	@Override
	public void insert(Connection conn, Personne bean) {
		
		Statement stmt = null;
		String requete = "insert into personne (nom, prenom, type) values (" + bean.getNom() + "," + bean.getPrenom() +"," + bean.getType() +");" ;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void delete(Connection conn, Personne bean) {
		
		Statement stmt = null;
		String requete = "delete personne where id = " + bean.getId() +" ;";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void update(Connection conn, Personne bean) {
		
		Statement stmt = null;
		String requete = "update personne set nom = " + bean.getNom() + 
				",  prenom = " + bean.getPrenom() +
				",  type = " + bean.getType() +
				"  where id = " + bean.getId()+ ";";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	
	}

	@Override
	public ArrayList<Personne> findAll(Connection conn) {

		Statement stmt = null;
		ResultSet result = null;
		ArrayList<Personne> listeResultat = new ArrayList<Personne>();
		
		String requete =  " select * from personne; " ;
		
		try {
			stmt = conn.prepareStatement(requete);
			result = stmt.executeQuery(requete);
			
			while(result.next()) {
				Personne personne = new Personne();   
				personne = new Personne(result.getInt(1),result.getString(2),result.getString(3),result.getString(4));
				listeResultat.add(personne);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				stmt.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return listeResultat;
	}	
}
