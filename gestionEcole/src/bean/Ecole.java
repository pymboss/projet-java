package bean;

public class Ecole {
	
	private Integer id;
	private String nom;
	
	public Ecole() {
	}
	
	public Ecole(String nom) {
		this.nom = nom;
	}
	
	public Ecole(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	

}
