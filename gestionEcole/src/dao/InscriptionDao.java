package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Inscription;

public class InscriptionDao extends GestionDao<Inscription> {

	@Override
	public Inscription find(Connection conn, Inscription bean) {
			ResultSet result = null;
			Statement stmt = null;
			Inscription inscriptionFound = new  Inscription();
			
			String requete =  " select  ID,  ID_CLASSE, C.NOM NOM_CLASSE,   "
					+ " ID_NIVEAU, N.NOM NOM_NIVEAU,  "
					+ " ID_ANNEE, DEBUT, FIN,  "
					+ " ID_PERSONNE, P.NOM NOM_PERSONNE, P.PRENOM,  "
					+ " from Inscription I, Classe C, Personne P, Ecole E, AnneeScolaire A, Niveau N "
					+ " WHERE I.ID_CLASSE = C.ID "
					+ " AND I.ID_PERSONNE = P.ID " 
					+ " AND C.ID_ECOLE = E.ID "
					+ " AND C.ID_NIVEAU = N.ID "
					+ " AND C.ID_ANNEE = A.ID "
					+ " where id  = "  + bean.getId() + " ;"  ;
			
			try {
				stmt = conn.createStatement();
				result = stmt.executeQuery(requete);
				if (result!=null && result.next())
				{
				   inscriptionFound.setId(result.getInt("ID"));
					inscriptionFound.getClasse().setId(result.getInt("ID_CLASSE"));
					inscriptionFound.getClasse().setNom(result.getString("NOM_CLASSE"));
					inscriptionFound.getClasse().getNiveau().setId(result.getInt("ID_NIVEAU"));
					inscriptionFound.getClasse().getNiveau().setNom(result.getString("NOM_NIVEAU"));
					inscriptionFound.getClasse().getAnneeScolaire().setDateDebut(result.getDate("DEBUT"));
					inscriptionFound.getClasse().getAnneeScolaire().setDateFin(result.getDate("FIN"));
					inscriptionFound.getEtudiant().setId(result.getInt("ID_PERSONNE"));
					inscriptionFound.getEtudiant().setNom(result.getString("NOM_PERSONNE"));
					inscriptionFound.getEtudiant().setPrenom(result.getString("PRENOM"));
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
			return inscriptionFound;
	}	

	@Override
	public void insert(Connection conn, Inscription bean) {
		Statement stmt = null;
		String requete = "insert into inscription (ID_CLASSE, ID_PERSONNE) values (" + bean.getClasse().getId() + "," + bean.getEtudiant().getId() +");" ;
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
	public void delete(Connection conn, Inscription bean) {
		Statement stmt = null;
		String requete = "delete inscription where id = " + bean.getId() +" ;";
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
	public void update(Connection conn, Inscription bean) {
		Statement stmt = null;
		String requete = "update inscription set id_classe = " + bean.getClasse().getId() + 
				",  id_personne = " + bean.getEtudiant().getId() +
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
	public ArrayList<Inscription> findAll(Connection conn) {
		Statement stmt = null;
		ResultSet result = null;
		ArrayList<Inscription> listeResultat = new ArrayList<Inscription>();
		
		String requete =  " select  ID,  ID_CLASSE, C.NOM NOM_CLASSE,   "
				+ " ID_NIVEAU, N.NOM NOM_NIVEAU,  "
				+ " ID_ANNEE, DEBUT, FIN,  "
				+ " ID_PERSONNE, P.NOM NOM_PERSONNE, P.PRENOM,  "
				+ " from Inscription I, Classe C, Personne P, Ecole E, AnneeScolaire A, Niveau N "
				+ " WHERE I.ID_CLASSE = C.ID "
				+ " AND I.ID_PERSONNE = P.ID " 
				+ " AND C.ID_ECOLE = E.ID "
				+ " AND C.ID_NIVEAU = N.ID "
				+ " AND C.ID_ANNEE = A.ID "
				+ " ; " ;
		
		try {
			stmt = conn.prepareStatement(requete);
			result = stmt.executeQuery(requete);
			
			while(result.next()) {
					Inscription inscription = new Inscription();   
					inscription.setId(result.getInt("ID"));
					inscription.getClasse().setId(result.getInt("ID_CLASSE"));
					inscription.getClasse().setNom(result.getString("NOM_CLASSE"));
					inscription.getClasse().getNiveau().setId(result.getInt("ID_NIVEAU"));
					inscription.getClasse().getNiveau().setNom(result.getString("NOM_NIVEAU"));
					inscription.getClasse().getAnneeScolaire().setDateDebut(result.getDate("DEBUT"));
					inscription.getClasse().getAnneeScolaire().setDateFin(result.getDate("FIN"));
					inscription.getEtudiant().setId(result.getInt("ID_PERSONNE"));
					inscription.getEtudiant().setNom(result.getString("NOM_PERSONNE"));
					inscription.getEtudiant().setPrenom(result.getString("PRENOM"));
					listeResultat.add(inscription);
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
