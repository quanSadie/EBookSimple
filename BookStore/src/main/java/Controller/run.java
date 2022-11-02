package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;

import DAO.BookDAO;
import DBConnect.DBUtils;

public class run {

	public static void main(String[] args) throws JSONException, IOException {
		ArrayList<String> list = new ArrayList<String>();
		DBUtils db = DBUtils.getInstance();
		String sql = "select book_isbn from users inner join users_books on users.user_id = users_books.user_id where users.user_id="
				+ 1;
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("book_isbn"));
			}
		} catch (Exception e) {
			Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		ArrayList<String> gg = new ArrayList<String>();
		gg.add("1583416080");
		System.out.println(list);
		System.out.println(gg);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).toString().equals("1583416080")) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
		}
	}

}
