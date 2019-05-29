package fr.bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.Operation;

/**
 * Cette classe fait un acces a une base de donnees. <br/>
 *
 * L'utilisation de JDBC se fait toujours de la meme maniere :
 * <ul>
 * <li>On charge le driver de la base de donnees</li>
 * <li>On demande au DriverManager un objet Connection</li>
 * <li>On demande a l'objet Connection un objet Statement (ou equivalent)</li>
 * <li>On demande a l'objet Statement d'executer la requete SQL</li>
 * <li>On recupere le resultat de la requete dans un objet ResultSet</li>
 * <li>Apres avoir fait le necessaire avec l'objet ResultSet on
 * <ul>
 * <li>ferme l'objet ResultSet</li>
 * <li>ferme l'objet Statement</li>
 * <li>ferme l'objet Connexion</li>
 * </ul>
 * </li>
 * </ul>
 * Exemple d'utilisation : <br/>
 * <div style='background:#FFFFFF; font-family: Courier New, Courier; font-size:
 * 10pt; COLOR: #000000; padding-bottom: 0px; padding-left: 0px; padding-right:
 * 0px; padding-top: 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color='#3f7f5f'>//&nbsp;Les&nbsp;informations&nbsp;vitales </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color=
 * '#7f0055'>final</font></strong>&nbsp;String&nbsp;lDriver&nbsp;=&nbsp;<font
 * color='#2a00ff'>"com.mysql.jdbc.Driver"</font>; <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color=
 * '#7f0055'>final</font></strong>&nbsp;String&nbsp;lUrl&nbsp;=&nbsp;<font color
 * ='#2a00ff'>"jdbc:mysql://localhost/banque?useSSL=false"; </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color=
 * '#7f0055'>final</font></strong>&nbsp;String&nbsp;lLogin&nbsp;=&nbsp;<font
 * color='#2a00ff'>"root"</font>; <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color=
 * '#7f0055'>final</font></strong>&nbsp;String&nbsp;lPwd&nbsp;=&nbsp;<font color
 * ='#2a00ff'>""</font>; <br/>
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=
 * '#3f7f5f'>//&nbsp;Declaration&nbsp;de&nbsp;la&nbsp;variable&nbsp;bd&nbsp;de&nbsp;type&nbsp;AccesBD
 * </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AccesBD&nbsp;bd&nbsp;=&nbsp;<strong><font
 * color='#7f0055'>null</font></strong>; <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer&nbsp;idUtilisateur&nbsp;=&nbsp;Integer.valueOf(1);
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color=
 * '#7f0055'>try</font></strong>&nbsp;{ <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color='#3f7f5f'>//&nbsp;Instantication&nbsp;de&nbsp;la&nbsp;variable
 * </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bd&nbsp;=&nbsp;<strong><font
 * color='#7f0055'>new</font></strong>&nbsp;AccesBD(lDriver); <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color=
 * '#3f7f5f'>//&nbsp;On&nbsp;se&nbsp;connecte&nbsp;a&nbsp;la&nbsp;base&nbsp;de&nbsp;donnees
 * </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bd.seConnecter(lUrl,&nbsp;lLogin,&nbsp;lPwd);
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color=
 * '#3f7f5f'>//&nbsp;On&nbsp;appel&nbsp;une&nbsp;des&nbsp;methodes&nbsp;metiers
 * </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;List<Compte>&nbsp;listeCpt&nbsp;=&nbsp;bd.selectCompte(idUtilisateur);
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color='#3f7f5f'>//&nbsp;Traitement </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println(listeCpt);
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<strong><font color=
 * '#7f0055'>catch</font></strong>&nbsp;(SQLException&nbsp;e)&nbsp;{ <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color='#3f7f5f'>//&nbsp;On&nbsp;gere&nbsp;le&nbsp;probleme </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;e.printStackTrace();
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;<strong><font color=
 * '#7f0055'>finally</font></strong>&nbsp;{ <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
 * color=
 * '#3f7f5f'>//&nbsp;Quoi&nbsp;qu'il&nbsp;arrive&nbsp;on&nbsp;se&nbsp;deconnecte&nbsp;de&nbsp;la&nbsp;base
 * </font><br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font
 * color='#7f0055'>if</font></strong>&nbsp;(bd&nbsp;!=&nbsp;<strong><font color=
 * '#7f0055'>null</font></strong>)&nbsp;{ <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bd.seDeconnecter();
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
 * <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<div style=
 * 'text-align:right;BORDER-TOP: #ccc 1px dashed'><a href=
 * "http://www.togotutor.com/code-to-html/java-to-html.php" target="_blank">Code
 * Formatted by ToGoTutor</a></div></div>
 */
public class AccesBD {
	private Connection cxt;

	/**
	 * Constructeur de la classe qui fournit une connection. <br/>
	 *
	 * C'est dans cette methode qu'est charge le driver. <br/>
	 *
	 * @param aDriverName
	 *            nom du Driver a charger
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public AccesBD(String aDriverName) throws SQLException {
		try {
			Class.forName(aDriverName);
		} catch (Exception cnf) {
			throw new SQLException("Impossible de charger le driver '" + aDriverName + "'", cnf);
		}
	}

	/**
	 * Methode de connexion de la base. <br/>
	 *
	 * C'est dans cette methode que l'on recupere un objet Connection
	 *
	 * @param aUrl
	 *            un url
	 * @param aLogin
	 *            un login
	 * @param aPassword
	 *            un mot de passe
	 * @throws SQLException
	 *             si un probleme survient
	 */
	public void seConnecter(String aUrl, String aLogin, String aPassword) throws SQLException {
		this.cxt = DriverManager.getConnection(aUrl, aLogin, aPassword);
	}

	/**
	 * Ferme tout.
	 *
	 * @param resultat
	 *            le result set
	 * @param request
	 *            le statement
	 */
	private final void closeAll(ResultSet resultat, Statement request) {
		// Tres IMPORTANT, on ferme tout
		if (resultat != null) {
			try {
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (request != null) {
			try {
				request.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Methode de deconnexion de la base. <br/>
	 *
	 * C'est dans cette methode que l'on ferme l'objet Connection
	 */
	public void seDeconnecter() {
		try {
			if (this.cxt != null) {
				this.cxt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Methode qui verifie que le login et le password vont bien ensemble. <br/>
	 *
	 * @param unLogin
	 *            un login
	 * @param unMdp
	 *            un mot de passe
	 * @return
	 *         <ul>
	 *         <li>null: si un probleme provient du login ou du mot de passe
	 *         </li>
	 *         <li>l'id du client si tout se passe bien</li>
	 *         </ul>
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public Integer authentifier(String unLogin, String unMdp) throws SQLException {
		if (this.cxt != null && !this.cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Creation de l'objet de requete
				request = this.cxt.prepareStatement("select id, password from utilisateur where login=?");
				request.setString(1, unLogin);

				// Envoie de la requete et recuperation du resultat
				resultat = request.executeQuery();

				// Parcours du resultat, toujours commencer par un .next
				while (resultat.next()) {
					Integer id = Integer.valueOf(resultat.getInt("id"));
					boolean noid = resultat.wasNull();
					String password = resultat.getString("password");
					if (noid) {
						return null;
					}
					return password == unMdp || password.equals(unMdp) ? id : null;
				}
				return null;
			} finally {
				this.closeAll(resultat, request);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Methode qui recupere toutes les operations d'un compte.
	 *
	 * @param unCompteId
	 *            un id de compte
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Operation> selectOperation(Integer unCompteId) throws SQLException {
		return this.selectOperation(unCompteId, null, null, null);
	}

	/**
	 * Methode qui recupere les operations d'un compte en fonction des criteres
	 * indiques.
	 *
	 * @param unCompteId
	 *            un id de compte
	 * @param dateDeb
	 *            date debut
	 * @param dateFin
	 *            date fin
	 * @param creditDebit
	 *            TRUE = credit, FALSE = debit, null = les deux
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Operation> selectOperation(Integer unCompteId, Date dateDeb, Date dateFin, Boolean creditDebit)
			throws SQLException {
		List<Operation> listeOperation = new ArrayList<Operation>();
		if (this.cxt != null && !this.cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				StringBuilder requete = new StringBuilder();
				requete.append("select * from operation where compteId=?");
				if (dateDeb != null) {
					requete.append(" and date >= ?");
				}
				if (dateFin != null) {
					// Probleme sur la date de fin car MySQL a des dates en
					// 2016-08-26 18:38:22
					// Mais nous on lui donne des date en 2016-08-26 00:00:00
					// Du coup, on doit gerer la date de fin en faisant +1 jour
					// Le < (et pas <=) evite d'avoir l'operation du lendemain a
					// 00:00:00
					requete.append(" and date < DATE_ADD(?, INTERVAL 1 DAY)");
				}
				if (creditDebit != null) {
					if (creditDebit.booleanValue()) {
						requete.append(" and montant >= 0");
					} else {
						requete.append(" and montant <= 0");
					}
				}
				requete.append(" order by date desc");
				// Recuperation de tous les clients
				request = this.cxt.prepareStatement(requete.toString());
				int idParam = 1;
				request.setInt(idParam, unCompteId.intValue());
				idParam++;
				if (dateDeb != null) {
					request.setDate(idParam, dateDeb);
					idParam++;
				}
				if (dateFin != null) {
					request.setDate(idParam, dateFin);
					idParam++;
				}
				resultat = request.executeQuery();
				while (resultat.next()) {
					int id = resultat.getInt("id");
					String libelle = resultat.getString("libelle");
					double montant = resultat.getDouble("montant");
					Timestamp date = resultat.getTimestamp("date");
					int compteId = resultat.getInt("compteId");
					listeOperation.add(new Operation(id, libelle, montant, date, compteId));
				}
			} finally {
				this.closeAll(resultat, request);
			}
			return listeOperation;
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Methode qui recupere un compte en fonction de son id.
	 *
	 * @param unCompteId
	 *            un id de compte
	 * @return le compte, null si pas trouve
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public Compte selectUnCompte(Integer unCompteId) throws SQLException {
		Compte cpt = null;
		if (this.cxt != null && !this.cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Recuperation de tous les clients
				request = this.cxt.prepareStatement("select * from compte where id=?");
				request.setInt(1, unCompteId.intValue());
				resultat = request.executeQuery();

				while (resultat.next()) {
					int id = resultat.getInt("id");
					double solde = resultat.getDouble("solde");
					double decouvert = resultat.getDouble("decouvert");
					// Par defaut, le getDouble renvoie 0 si la valeur est null
					// Si on veut tester le null il faut faire
					boolean pasDeDecouver = resultat.wasNull();
					double taux = resultat.getDouble("taux");
					boolean pasDeTaux = resultat.wasNull();
					if (pasDeDecouver && pasDeTaux) {
						cpt = new Compte(id, solde);
					} else if (pasDeDecouver && !pasDeTaux) {
						cpt = new CompteRemunere(id, solde, taux);
					} else if (!pasDeDecouver && pasDeTaux) {
						cpt = new CompteASeuil(id, solde, decouvert);
					} else {
						cpt = new CompteASeuilRemunere(id, solde, taux, decouvert);
					}
					cpt.setLibelle(resultat.getString("libelle"));
				}
			} finally {
				this.closeAll(resultat, request);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
		return cpt;
	}

	/**
	 * Methode qui recupere tous les comptes d'un utilisateur.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @return la liste de ses comptes, une liste vide si aucun
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Compte> selectCompte(Integer unUtilisateurId) throws SQLException {
		List<Compte> listeCompte = new ArrayList<Compte>();
		if (this.cxt != null && !this.cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Recuperation de tous les clients
				request = this.cxt.prepareStatement("select * from compte where utilisateurId=?");
				request.setInt(1, unUtilisateurId.intValue());
				resultat = request.executeQuery();
				while (resultat.next()) {
					int id = resultat.getInt("id");
					// String libelle = resultat.getString("libelle");
					double solde = resultat.getDouble("solde");
					double decouvert = resultat.getDouble("decouvert");
					// Par defaut, le getDouble renvoie 0 si la valeur est null
					// Si on veut tester le null il faut faire
					boolean pasDeDecouver = resultat.wasNull();
					double taux = resultat.getDouble("taux");
					boolean pasDeTaux = resultat.wasNull();
					Compte cpt = null;
					if (pasDeDecouver && pasDeTaux) {
						cpt = new Compte(id, solde);
					} else if (pasDeDecouver && !pasDeTaux) {
						cpt = new CompteRemunere(id, solde, taux);
					} else if (!pasDeDecouver && pasDeTaux) {
						cpt = new CompteASeuil(id, solde, decouvert);
					} else {
						cpt = new CompteASeuilRemunere(id, solde, taux, decouvert);
					}
					cpt.setLibelle(resultat.getString("libelle"));
					listeCompte.add(cpt);
				}
			} finally {
				this.closeAll(resultat, request);
			}
			return listeCompte;
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Methode qui recupere tous les utilisateurs avec leurs comptes.
	 *
	 * @return la liste des utilisateurs avec leurs comptes
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Client> selectUtilisateur() throws SQLException {
		List<Client> listeClient = new ArrayList<Client>();
		if (this.cxt != null && !this.cxt.isClosed()) {
			Statement request = null;
			ResultSet resultat = null;
			try {
				// Recuperation de tous les clients
				request = this.cxt.createStatement();
				resultat = request.executeQuery("select * from utilisateur");
				while (resultat.next()) {
					int id = resultat.getInt("id");
					String nom = resultat.getString("nom");
					String prenom = resultat.getString("prenom");
					Date dateNaissance = resultat.getDate("dateDeNaissance");
					int age = -1;
					if (dateNaissance != null) {
						// On calcul l'age
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(System.currentTimeMillis());
						int anneeMaintenant = c.get(Calendar.YEAR);
						c.setTime(dateNaissance);
						int anneeNaissance = c.get(Calendar.YEAR);
						age = anneeMaintenant - anneeNaissance;
					}
					Client cl = new Client(id, nom, prenom, age);
					listeClient.add(cl);
				}

				// Recuperation de tous les comptes pour chaque client
				Iterator<Client> iterClient = listeClient.iterator();
				while (iterClient.hasNext()) {
					Client client = iterClient.next();
					List<Compte> listeCpt = this.selectCompte(Integer.valueOf(client.getNumero()));
					Iterator<Compte> iterCompte = listeCpt.iterator();
					while (iterCompte.hasNext()) {
						client.ajouterCompte(iterCompte.next());
					}
				}
			} finally {
				this.closeAll(resultat, request);
			}
			return listeClient;
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Effectue un virement entre deux comptes.
	 *
	 * @param cptSrc
	 *            le compte source
	 * @param cptDest
	 *            le compte destination
	 * @param unMontant
	 *            le montant qui sera retire du compte source et ajoute au
	 *            compte destination
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public void faireVirement(Integer cptSrc, Integer cptDest, Double unMontant) throws SQLException {
		if (this.cxt != null && !this.cxt.isClosed()) {
			this.cxt.setAutoCommit(false);
			PreparedStatement request = null;
			try {
				// Recuperation de tous les clients
				request = this.cxt.prepareStatement("update compte set solde=(solde-?) where id=?");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptSrc.intValue());
				request.executeUpdate();
				request.close();
				request = this.cxt.prepareStatement("update compte set solde=(solde+?) where id=?");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptDest.intValue());
				request.executeUpdate();
				request.close();
				request = this.cxt.prepareStatement(
						"insert into operation (libelle, montant, date, compteId) values ('Virement',?,NOW(),?)");
				request.setDouble(1, -unMontant.doubleValue());
				request.setInt(2, cptSrc.intValue());
				request.executeUpdate();
				request.close();
				request = this.cxt.prepareStatement(
						"insert into operation (libelle, montant, date, compteId) values ('Virement',?,NOW(),?)");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptDest.intValue());
				request.executeUpdate();
				this.cxt.commit();
			} catch (SQLException sql) {
				this.cxt.rollback();
				// On la relance
				throw sql;
			} finally {
				this.cxt.setAutoCommit(true);
				this.closeAll(null, request);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}
}