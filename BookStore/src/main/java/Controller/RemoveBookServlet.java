package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;

/**
 * Servlet implementation class RemoveBookServlet
 */
@WebServlet("/RemoveBookServlet")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		String bookid = (String) request.getParameter("bid");
		BookDAO dao = new BookDAO();

		// kiem tra truong hop xoa sach thi phai xoa cac du lieu tu cac bang khac lien
		// quan
		// toi sach do
		if (action.equals("remove")) {
			dao.RmvBook(bookid);
			response.sendRedirect("admin-page.jsp");
		}

		// Update book
//		if (action.equals("Update")) {
//			dao.updUser(request.getParameter("updID"), request.getParameter("updName"),
//					request.getParameter("updEmail"), request.getParameter("updPass"), request.getParameter("updAd"));
//			response.sendRedirect("admin-page.jsp");
//		}
	}

}
