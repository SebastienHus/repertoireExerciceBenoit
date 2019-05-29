package fr.banque;

import java.sql.Timestamp;

/**
 * Classe representant une operation.
 */
public class Operation extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private String libelle;
	private Double montant;
	private Timestamp date;

	private Integer compteId;

	/**
	 * Constructeur. <br/>
	 */
	public Operation() {
		this(null, null, null, null, null);
	}

	/**
	 * Constructeur.
	 *
	 * @param unNumero
	 *            un numero d'operation
	 * @param unLibelle
	 *            un libelle
	 * @param unMontant
	 *            un montant
	 * @param uneDate
	 *            une date
	 * @param unCompteId
	 *            un id de compte
	 *
	 */
	public Operation(Integer unNumero, String unLibelle, Double unMontant, Timestamp uneDate, Integer unCompteId) {
		super(unNumero);
		this.setLibelle(unLibelle);
		this.setMontant(unMontant);
		this.setDate(uneDate);
		this.setCompteId(unCompteId);
	}

	/**
	 * Recupere la valeur de l'attribut. <br/>
	 *
	 * @return la veleur de libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Modifie la valeur de l'attribut. <br/>
	 *
	 * @param libelle
	 *            la nouvelle valeur de libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Recupere la valeur de l'attribut. <br/>
	 *
	 * @return la veleur de montant
	 */
	public Double getMontant() {
		return this.montant;
	}

	/**
	 * Modifie la valeur de l'attribut. <br/>
	 *
	 * @param montant
	 *            la nouvelle valeur de montant
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * Recupere la valeur de l'attribut. <br/>
	 *
	 * @return la veleur de date
	 */
	public Timestamp getDate() {
		return this.date;
	}

	/**
	 * Modifie la valeur de l'attribut. <br/>
	 *
	 * @param date
	 *            la nouvelle valeur de date
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * Recupere la valeur de l'attribut. <br/>
	 *
	 * @return la veleur de compteId
	 */
	public Integer getCompteId() {
		return this.compteId;
	}

	/**
	 * Modifie la valeur de l'attribut. <br/>
	 *
	 * @param compteId
	 *            la nouvelle valeur de compteId
	 */
	public void setCompteId(Integer compteId) {
		this.compteId = compteId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" Libelle=");
		builder.append(this.getLibelle());
		builder.append(", montant=");
		builder.append(this.getMontant());
		builder.append(", date=");
		builder.append(this.getDate());
		builder.append(", compteId=");
		builder.append(this.getCompteId());
		builder.append("]");
		return builder.toString();
	}
}
