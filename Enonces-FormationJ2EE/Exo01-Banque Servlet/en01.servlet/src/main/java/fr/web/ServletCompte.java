
package fr.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Compte;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher tous les comptes d'un client. <br/>
 */
@WebServlet(description = "Affiche tous les comptes d'un client", urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletCompte() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Code me en faisant usage de fr.bd.AccesBD

		try
		{

			AccesBD connection = new AccesBD("com.mysql.jdbc.Driver");

			connection.seConnecter("jdbc:mysql://localhost:3306/banque", "root", "root");

			List<Compte> userListAccount =  connection.selectCompte(Integer.parseInt(request.getParameter("userId")));

			request.setAttribute("userListAccount", userListAccount);

			this.getServletContext().getRequestDispatcher("/WEB-INF/userAccount.jsp").forward(request, response);

			connection.seDeconnecter();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
