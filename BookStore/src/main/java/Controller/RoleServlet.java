package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import Model.Account;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/profile")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String thisRole = request.getParameter("role");
		if (thisRole.equals("0")) {
			HttpSession session = request.getSession();
			AccountDAO dao = new AccountDAO();
			Account a = dao.displayOneUser((String) request.getSession().getAttribute("this_id"));
			session.setAttribute("person", a);
			response.sendRedirect("userPage.jsp");
		} else if (thisRole.equals("1")) {
			HttpSession session = request.getSession();
			response.sendRedirect("StaffBookServlet");
		}
	}

}
