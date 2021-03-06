/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;

/**
 *
 * @author pymas
 */
public class Trimestre {
    
        private Integer id;
	private Integer numero;
	private Date dateDebut;
	private Date dateFin;
	
    /**
     *
     */
    public Trimestre() {
	}
	
    /**
     *
     * @param numero
     * @param dateDebut
     * @param dateFin
     */
    public Trimestre(Integer numero, Date dateDebut, Date dateFin) {
		this.numero = numero;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

    /**
     *
     * @param id
     * @param numero
     * @param dateDebut
     * @param dateFin
     */
    public Trimestre(Integer id, Integer numero, Date dateDebut, Date dateFin) {
		this(numero,dateDebut ,dateFin);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}
