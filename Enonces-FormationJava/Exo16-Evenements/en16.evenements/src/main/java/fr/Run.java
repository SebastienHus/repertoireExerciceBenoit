package fr;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;

/**
 * La classe de lancement. <br/>
 */
public class Run {

	/**
	 * Lancement des tests. <br>
	 *
	 * @param args
	 *            les arguments de lancement
	 */
	public static void main(String[] args) {
		// creation du client principale Mr Dupont
		Client client = new Client(Integer.valueOf(1), "Dupont", "Henry", Integer.valueOf(28));
		// Creation des comptes
		Compte c1 = new Compte(Integer.valueOf(1), Double.valueOf(200));
		Compte c2 = new CompteASeuil(Integer.valueOf(2), Double.valueOf(20000), Double.valueOf(200));
		Compte c3 = new CompteRemunere(Integer.valueOf(3), Double.valueOf(200), Double.valueOf(0.05));
		Compte c4 = new CompteASeuilRemunere(Integer.valueOf(4), Double.valueOf(200), Double.valueOf(0.1),
				Double.valueOf(10));
		client.ajouterCompte(c1);
		client.ajouterCompte(c2);
		client.ajouterCompte(c3);
		client.ajouterCompte(c4);
		System.out.println(client);

		// On fait des manipulations sur c1 et c2 pour voir si le banquier les
		// recuperes
		c1.ajouter(Double.valueOf(100));
		try {
			c2.retirer(Double.valueOf(125));
		} catch (BanqueException e) {
			e.printStackTrace();
		}
		// Le banquier n'est pas lie a c3
		c3.ajouter(Double.valueOf(25));
	}
}