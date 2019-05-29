package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui va gerer le login du client. <br/>
 */
@WebServlet(urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletLogin() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 - Part vers la page login.jsp
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 - Recupere les parametres du fomulaire
		// Verifie qu'ils ne sont ni null ni vides
		// Si null ou vides => Direction page login avec un attribut message
		// place dans la request
		// Sinon, on continue
		// 2 - Se connecte a la base de donnees via fr.bd.AccesBD
		// Recupere une information via la methode authentifier de AccesBD
		// Place l'information dans le scope session
		// Part vers la page menu.jsp
		// 3 - Si un probleme ou une erreur survient lors de la recuperation des
		// donnees
		// On place un message dans la request et on va vers la page login
	}
}