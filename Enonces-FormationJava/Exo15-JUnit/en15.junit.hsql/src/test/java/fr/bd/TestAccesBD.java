package fr.bd;

/**
 * Classe de test en JUnit 4 pour la classe fr.bd.AccesBD. <br/>
 */
@SuppressWarnings("unused")
public class TestAccesBD {
	// Nom du driver pour acceder a la base de donnees.
	// Lire la documentation associee a sa base de donnees pour le connaitre
	private final String dbDriver = "org.hsqldb.jdbc.JDBCDriver";
	// URL d'access a la base de donnees.
	private final String dbUrl = "jdbc:hsqldb:hsql://localhost/banque";
	// Login d'access a la base de donnees.
	private final String dbLogin = "SA";
	// Mot de passe d'access a la base de donnees.
	private final String dbPwd = "";

}
