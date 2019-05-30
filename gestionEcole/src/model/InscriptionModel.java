package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import bean.Inscription;

public class InscriptionModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4081299893555591675L;

	private List<Inscription> inscriptions;
	
	public InscriptionModel (List<Inscription> inscriptions)
	{
		this.inscriptions=inscriptions;
	}
	
	private final String[] entetes = { "Identifiant", "Nom Classe", "Niveau" ,"Date debut","Date fin", "Nom", "Prenom"};
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return inscriptions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			// identifiant
			return inscriptions.get(rowIndex).getId();

		case 1:
			//  Classe
			return inscriptions.get(rowIndex).getClasse().getNom();

		case 2:
			// Niveau
			return inscriptions.get(rowIndex).getClasse().getNiveau().getNom();

		case 3:
			// Annee scolaire
			return inscriptions.get(rowIndex).getClasse().getAnneeScolaire().getDateDebut();
		
		case 4:
			// Annee scolaire
			return inscriptions.get(rowIndex).getClasse().getAnneeScolaire().getDateFin();
			
		case 5:
			// Annee scolaire
			return inscriptions.get(rowIndex).getEtudiant().getNom();
			
		case 6:
			// Annee scolaire
			return inscriptions.get(rowIndex).getEtudiant().getPrenom();
				
		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	
	
	public List<Inscription> getInscriptions() {
		return inscriptions;
	}
}
