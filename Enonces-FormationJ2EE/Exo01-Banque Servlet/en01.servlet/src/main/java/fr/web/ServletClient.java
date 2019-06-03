package fr.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Client;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher tous les clients. <br/>
 */
@WebServlet(description = "Affiche tous les clients", urlPatterns = { "/ServletClient" })
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletClient() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// connection bdd

		try
		{

			AccesBD connection = new AccesBD("com.mysql.jdbc.Driver");

			connection.seConnecter("jdbc:mysql://localhost:3306/banque", "root", "root");

			List<Client> userList= connection.selectUtilisateur();

			request.setAttribute("userList", userList);

			connection.seDeconnecter();

			this.getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


}
