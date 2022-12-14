package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fn = request.getParameter("signUpName");
		String em = request.getParameter("signUpEmail");
		String un = request.getParameter("signUpUsername");
		String pw = request.getParameter("signUpPassword");
		String ia = "0";
		AccountDAO dao = new AccountDAO();
		boolean checkuser = dao.signUpUser(em, un, pw, 1, fn, 0);
		HttpSession session = request.getSession();
		if (checkuser) {
			// neu dang ky thanh cong -> thong bao dang ky thanh cong
			session.setAttribute("tb", "Sign up successfully!");
			response.sendRedirect("index.jsp");
		} else {
			// neu trung ten dang nhap -> dang ky that bai
			session.setAttribute("tb", "Username existed!");
			response.sendRedirect("login.jsp");
		}

	}

}
