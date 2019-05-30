package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Classe;

public class ClasseDao extends GestionDao<Classe> {

	@Override
	public Classe find(Connection conn, Classe bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Connection conn, Classe bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, Classe bean) {
		
		Statement stmt = null;
		String requete = "delete classe " + 
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
	public void update(Connection conn, Classe bean) {
		Statement stmt = null;
		String requete = "update classe set nom = " + bean.getNom() + 
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
	public ArrayList<Classe> findAll(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}



}
