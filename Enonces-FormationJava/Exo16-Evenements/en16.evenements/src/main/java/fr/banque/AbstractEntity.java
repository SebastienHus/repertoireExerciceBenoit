package fr.banque;

import java.io.Serializable;

/**
 * Ceci est la classe parente de tous les objets representes en base de donnees.
 * <br>
 */
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numero;

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 */
	public AbstractEntity(Integer unNumero) {
		super();
		this.setNumero(unNumero);
	}

	/**
	 * Donne acces au numero du compte. <br>
	 *
	 * @return le numero du compte
	 */
	public Integer getNumero() {
		return this.numero;
	}

	/**
	 * Modificateur pour la propriete numero.
	 *
	 * @param unNumero
	 *            un numero
	 */
	protected final void setNumero(Integer unNumero) {
		this.numero = unNumero;
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
		sb.append(this.getClass().getName());
		sb.append(" Id=");
		sb.append(this.getNumero());
		return sb.toString();
	}

	/**
	 * Indique si deux comptes sont egaux. <br>
	 *
	 * Deux comptes sont egaux si ils ont le meme numero d'identification.
	 *
	 * @param obj
	 *            l'objet qui sera compare a this
	 * @return <code>true</code> si les deux sont egaux et <code>false</code>
	 *         sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof AbstractEntity && obj.getClass() == this.getClass()) {
			AbstractEntity c = (AbstractEntity) obj;
			return this.getNumero() == c.getNumero()
					|| this.getNumero() != null && this.getNumero().equals(c.getNumero());
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (this.getNumero() != null) {
			return (this.getClass().getName() + "_" + this.getNumero()).toString().hashCode();
		}
		return super.hashCode();
	}
}
