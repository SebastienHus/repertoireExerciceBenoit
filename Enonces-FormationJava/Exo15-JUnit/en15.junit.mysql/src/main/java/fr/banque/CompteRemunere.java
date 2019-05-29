package fr.banque;

/**
 * Ceci est la classe Compte remunere. <br>
 */
public class CompteRemunere extends Compte implements ICompteRemunere {
	private static final long serialVersionUID = 1L;
	private Double taux;

	/**
	 * Constructeur.
	 */
	public CompteRemunere() {
		this(null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 * @param unTaux
	 *            un taux entre [0, 1[
	 */
	public CompteRemunere(Integer unNumero, Double unSoldeInitial, Double unTaux) {
		super(unNumero, unSoldeInitial);
		this.setTaux(unTaux);
	}

	@Override
	public Double getTaux() {
		return this.taux;
	}

	@Override
	public void setTaux(Double taux) {
		if (taux.doubleValue() < 0 || taux.doubleValue() >= 1) {
			throw new IllegalArgumentException("Le taux doit etre entre [0, 1[");
		}
		this.taux = taux;
	}

	@Override
	public Double calculerInterets() {
		return Double.valueOf(super.getSolde().doubleValue() * this.getTaux().doubleValue());
	}

	@Override
	public void verserInterets() {
		super.ajouter(this.calculerInterets());
	}

	@Override
	public String toString() {
		return super.toString() + " Taux=" + this.getTaux();
	}

}
