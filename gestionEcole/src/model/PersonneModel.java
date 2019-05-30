package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import bean.Personne;

public class PersonneModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4081299893555591675L;

	private List<Personne> personnes;
	
	public PersonneModel (List<Personne> personnes)
	{
		this.personnes=personnes;
	}
	
	private final String[] entetes = { "Identifiant", "Nom", "Prénom", "Type" };
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return personnes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			// identifiant
			return personnes.get(rowIndex).getId();

		case 1:
			// Nom
			return personnes.get(rowIndex).getNom();

		case 2:
			// Prenom
			return personnes.get(rowIndex).getPrenom();

		case 3:
			// Type
			return personnes.get(rowIndex).getType();
		
		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	public List<Personne> getPersonnes() {
		return personnes;
	}
}
