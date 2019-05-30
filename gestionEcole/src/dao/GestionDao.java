package dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class  GestionDao <T> {
	
	
	public abstract T find   (Connection conn, T bean) ;
	
	public abstract void insert (Connection conn, T bean) ;
	
	public abstract void delete (Connection conn, T bean) ; 
	
	public abstract void update (Connection conn, T bean) ;
	
	public abstract ArrayList<T> findAll (Connection conn) ;
	
}
