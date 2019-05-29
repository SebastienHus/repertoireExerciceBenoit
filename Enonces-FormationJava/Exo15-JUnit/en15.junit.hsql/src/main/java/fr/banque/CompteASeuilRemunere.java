package fr.banque;

/**
 * Ceci est la classe Compte A seuil remunere. <br/>
 * On a choisit ici de prendre comme parent le CompteRemunere. Ce choix est base
 * sur le fait que c'est la classe qui a le plus de code. Ce qui nous fait le
 * moins de choses a recopier.
 */
public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil {
	private static final long serialVersionUID = 1L;
	private Double seuil;

	/**
	 * Constructeur. <br/>
	 */
	public CompteASeuilRemunere() {
		this(null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 * @param unTaux
	 *            un taux [0, 1[
	 * @param unSeuil
	 *            un seuil
	 */
	public CompteASeuilRemunere(Integer unNumero, Double unSoldeInitial, Double unTaux, Double unSeuil) {
		super(unNumero, unSoldeInitial, unTaux);
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
