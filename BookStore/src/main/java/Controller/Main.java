
package Controller;

import java.io.IOException;

import org.json.JSONException;

import DAO.BookDAO;

public class Main {
	public static void main(String[] args) throws IOException, JSONException {
		BookDAO dao = new BookDAO();
		System.out.println(dao.searchBooks("python"));
	}
}
