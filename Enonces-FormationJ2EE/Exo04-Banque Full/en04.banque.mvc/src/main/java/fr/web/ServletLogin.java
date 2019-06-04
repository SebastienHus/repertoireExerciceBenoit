package fr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.bd.AccesBD;

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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msgLogin = "" ;
		String msgPass = "" ;

		// 1 - Recupere les parametres du fomulaire

		String login = request.getParameter("inLogin");
		String pass = request.getParameter("inPass");


		// Verifie qu'ils ne sont ni null ni vides

		if(!login.isEmpty() && !pass.isEmpty())
		{
			try
			{
				int userId = AccesBD.getInstance().authentifier(login, pass);

				if((userId == -1) || (userId == -2))
				{
					if(userId == -1)
					{
						msgLogin = "Votre login n'est pas reconnu !";
						request.setAttribute("msgLogin", msgLogin);
					}
					if(userId == -2)
					{
						msgPass = "Votre mot de passe ne correspond pas à votre login !";
						request.setAttribute("msgPass", msgPass);
					}

					RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					//je cré une session avec le userId qui me permettra de verifier sur chaque page si je suis co
					//je lance une session
					HttpSession session = request.getSession(true);
					//j affect une variable persistante dans ma session
					session.setAttribute("userId", userId);

					//je suis rediriger sur la page menu
					response.sendRedirect(request.getContextPath() + "/menu.jsp");


				}

			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{

			/*if(login.isEmpty())
			{
				msgLogin = "Vous n'avez pas renseigné votre login" ;
				request.setAttribute("msgLogin", msgLogin);
			}

			if(pass.isEmpty())
			{
				msgPass = "Vous n'avez pas renseigné votre mot de passe" ;
				request.setAttribute("msgPass", msgPass);
			}*/

			if(login.equals(""))
			{
				msgLogin = "Vôtre login est vide" ;
				request.setAttribute("msgLogin", msgLogin);
			}

			if(pass.equals(""))
			{
				msgPass = "Vôtre mot de passe est vide" ;
				request.setAttribute("msgPass", msgPass);
			}



			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}


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
