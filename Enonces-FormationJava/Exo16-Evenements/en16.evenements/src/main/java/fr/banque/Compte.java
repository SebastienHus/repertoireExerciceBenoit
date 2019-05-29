package fr.banque;

/**
 * Ceci est la classe Compte. <br>
 */
public class Compte extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private Double solde;
	private String libelle;

	/**
	 * Constructeur de l'objet. <br>
	 * Le numero par defaut sera -1.
	 */
	public Compte() {
		this(null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 */
	public Compte(Integer unNumero, Double unSoldeInitial) {
		super(unNumero);
		this.setSolde(unSoldeInitial);
	}

	/**
	 * Donne acces au solde du compte. <br>
	 *
	 * @return le solde du compte
	 */
	public Double getSolde() {
		return this.solde;
	}

	/**
	 * Fixe le solde du compte. <br>
	 *
	 * @param unSolde
	 *            le nouveau solde du compte
	 */
	protected final void setSolde(Double unSolde) {
		this.solde = unSolde;
	}

	/**
	 * Ajoute un montant au compte. <br>
	 *
	 * @param unMontant
	 *            le montant ajoute au compte
	 */
	public void ajouter(Double unMontant) {
		this.setSolde(Double.valueOf(this.getSolde().doubleValue() + unMontant.doubleValue()));
	}

	/**
	 * Retire un montant du compte. <br>
	 *
	 * @param unMontant
	 *            le montant retire du compte
	 * @throws BanqueException
	 *             si un probleme survient
	 */
	public void retirer(Double unMontant) throws BanqueException {
		this.setSolde(Double.valueOf(this.getSolde().doubleValue() - unMontant.doubleValue()));
	}

	/**
	 * Formatage du compte sous forme de String utilisable directement par
	 * System.out.println(..);. <br>
	 *
	 * @return une representation chainee de l'objet
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" Solde=");
		sb.append(this.getSolde());
		sb.append(" Libelle=");
		sb.append(this.getLibelle());
		return sb.toString();
	}

	/**
	 * Recupere le libelle du compte.
	 *
	 * @return le libelle du compte.
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Modifie le libelle du compte.
	 *
	 * @param aLibelle
	 *            le libelle du compte.
	 */
	public void setLibelle(String aLibelle) {
		this.libelle = aLibelle;
	}
}
