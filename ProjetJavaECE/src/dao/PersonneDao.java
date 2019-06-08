/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modele.Personne;
/**
 *
 * @author pymas
 */
public class PersonneDao extends ParametresDao<Personne>{

    public PersonneDao(Connection conn) {
        super(conn);
    }


    @Override
    public void create(Personne obj,Connection conn) {
        try
        {

        String request = "INSERT INTO personne (nom, prenom, type) values (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(request);
        statement.setObject(1, obj.getNom());
        statement.setObject(2, obj.getPrenom());
        statement.setObject(3, obj.getType());

        statement.executeUpdate();
        } catch (SQLException e) {
        } 
    }

    @Override
    public void delete(Personne obj,Connection conn) {
        try
        {

        String request = "DELETE FROM personne WHERE id = " + obj.getId();
        PreparedStatement statement = conn.prepareStatement(request);
        statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(Personne obj,Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personne find(Personne obj,Connection conn) {
        ResultSet result = null;
        Statement stmt = null;
        Personne personneFound = null;
        String request = "SELECT id, nom, prenom, type FROM personne WHERE id  = "  + obj.getId() ;
        try {
                stmt = conn.createStatement();
                result = stmt.executeQuery(request);
                if (result!=null && result.next())
                {
                        personneFound = new Personne(result.getInt(1),result.getString(2),result.getString(3),result.getString(4));
                }

        } catch (SQLException e) {
        }
        return personneFound;
    }
    
}
