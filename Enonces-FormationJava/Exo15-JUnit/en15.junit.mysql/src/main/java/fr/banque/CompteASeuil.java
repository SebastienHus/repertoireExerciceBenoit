package fr.banque;

/**
 * Ceci est la classe Compte a seuil. <br/>
 * Il n'est pas possible de descendre en dessous du seuil.
 */
public class CompteASeuil extends Compte implements ICompteASeuil {
	private static final long serialVersionUID = 1L;
	private Double seuil;

	/**
	 * Constructeur. <br/>
	 */
	public CompteASeuil() {
		this(null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 * @param unSeuil
	 *            un seuil
	 */
	public CompteASeuil(Integer unNumero, Double unSoldeInitial, Double unSeuil) {
		super(unNumero, unSoldeInitial);
		this.setSeuil(unSeuil);
	}

	@Override
	public Double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(Double unSeuil) {
		this.seuil = unSeuil;
	}

	@Override
	public String toString() {
		return super.toString() + " Seuil=" + this.getSeuil();
	}

	@Override
	public void retirer(Double unMontant) throws BanqueException {
		double simu = this.getSolde().doubleValue() - unMontant.doubleValue();
		if (simu <= this.getSeuil().doubleValue()) {
			throw new BanqueException("Votre seuil de " + this.getSeuil() + " ne vous permet pas de retirer "
					+ unMontant + " de votre compte " + this.getNumero());
		} else {
			super.retirer(unMontant);
		}
	}
}
