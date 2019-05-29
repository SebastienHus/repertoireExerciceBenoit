package fr.banque;

import java.io.Serializable;

/**
 * Interface representant un compte a seuil.
 */
public interface ICompteASeuil extends Serializable {

	/**
	 * Retire l'argent si c'est possible.
	 *
	 * @param unMontant
	 *            un montant a retirer
	 * @throws BanqueException
	 *             si une erreur survient
	 */
	public abstract void retirer(Double unMontant) throws BanqueException;

	/**
	 * Recupere le seuil.
	 *
	 * @return le seuil
	 */
	public abstract Double getSeuil();

	/**
	 * Modifie la valeur du seuil.
	 *
	 * @param unSeuil
	 *            le nouveau seuil
	 */
	public abstract void setSeuil(Double unSeuil);

}