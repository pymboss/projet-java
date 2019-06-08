/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;


/**
 *
 * @author pymas
 * @param <T>
 */
public abstract class ParametresDao <T> {
    
        protected Connection conn = null;

        public ParametresDao(Connection conn){
          this.conn = conn;
        }

        /**
        * Méthode de création
        * @param obj 
     * @param conn 
        */
        public abstract void create(T obj,Connection conn);

        /**
        * Méthode pour effacer
        * @param obj 
     * @param conn 
        */
        public abstract void delete(T obj, Connection conn);

        /**
        * Méthode de mise à jour
        * @param obj
     * @param conn
        */
        public abstract void update(T obj, Connection conn);

        /**
        * Méthode de recherche des informations
        * @param obj
     * @param conn
        * @return T
        */
        public abstract T find(T obj, Connection conn);
}
