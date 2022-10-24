package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DBConnect.DBUtils;
import Model.Book;

public class BookDAO {

	// -------------------------- Doc du lieu JSON tu URL --------------------------
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	// ----------------------------------------------------------------------------

	// -------------------------- Hien thi sach --------------------------
	public ArrayList<Book> getBooks() {
		ArrayList<Book> list = new ArrayList<Book>();
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from books";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new Book(rs.getString("title"), rs.getString("cover"), rs.getString("author"),
						rs.getString("category"), rs.getString("price"), rs.getString("description"),
						rs.getString("isbn"), rs.getString("readLink")));
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
		return list;
	}

	// -----------------------------------------------------------
	// -------------------------- Hien thi sach --------------------------
	public ArrayList<Book> getUsersBooks() {
		ArrayList<Book> list = new ArrayList<Book>();
		DBUtils db = DBUtils.getInstance();
		String sql = "\r\n" + "select * from books inner join users_books\r\n"
				+ "on books.book_id = users_books.book_id inner join users\r\n"
				+ "on users.user_id = users_books.user_id\r\n" + " ";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new Book(rs.getString("title"), rs.getString("cover"), rs.getString("author"),
						rs.getString("category"), rs.getString("price"), rs.getString("description"),
						rs.getString("isbn"), rs.getString("readLink")));
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
		return list;
	}

	// -------------------------- Tim sach theo tu khoa --------------------------
	public ArrayList<Book> searchBooks(String searchKey) throws JSONException, IOException {
		ArrayList<Book> list = new ArrayList<Book>();
		Random rd = new Random();
		String url = "https://www.googleapis.com/books/v1/volumes?q=";
		JSONObject json = readJsonFromUrl(url + searchKey);
		JSONArray arr = json.getJSONArray("items");
		try {
			for (int i = 0; i < arr.length(); i++) {
				int pr = rd.nextInt(100 - 10 + 1) + 10;
				Book b = new Book(arr.getJSONObject(i).getJSONObject("volumeInfo").getString("title"),
						arr.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks")
								.getString("thumbnail"),
						(String) arr.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").get(0),
						"Free ebooks", pr + "$",
						arr.getJSONObject(i).getJSONObject("volumeInfo").getString("description"),
						arr.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("industryIdentifiers")
								.getJSONObject(0).getString("identifier"),
						arr.getJSONObject(i).getJSONObject("volumeInfo").getString("previewLink"));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// --------------------------------------------------------------------------------

	// -------------------------- Hien thi chi tiet sach
	// ------------------------------
	public Book showBookDetail(String isbn_13) throws JSONException, IOException {
		Random rd = new Random();
		Book b = new Book();
		String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
		JSONObject json = readJsonFromUrl(url + isbn_13);
		JSONArray arr = json.getJSONArray("items");
		for (int i = 0; i < arr.length(); i++) {
			b.setTitle(arr.getJSONObject(i).getJSONObject("volumeInfo").getString("title"));
			b.setDescription(arr.getJSONObject(i).getJSONObject("volumeInfo").getString("description"));
			b.setImageUrl(arr.getJSONObject(i).getJSONObject("volumeInfo").getJSONObject("imageLinks")
					.getString("thumbnail"));
			b.setIsbn(isbn_13);
			b.setPrice("FREE");
			b.setReadLink(arr.getJSONObject(i).getJSONObject("volumeInfo").getString("previewLink"));
		}
		return b;
	}

	// -------------------------- Them sach vao database --------------------------
	public void AddBook(Book b) {
		DBUtils db = DBUtils.getInstance();
		String bookSql = "insert into books(title,cover,author, category, price,description, isbn, readlink) values(?,?,?,?,?,?,?,?);";
		Connection con = null;

		// Them tac gia

		// Them sach
		try {
			con = db.getConnection();
			// ---------
			PreparedStatement statement = null;
			// ---------

			statement = con.prepareStatement(bookSql);
			statement.setString(1, b.getTitle());
			statement.setString(2, b.getImageUrl());
			statement.setString(3, b.getAuthor());
			statement.setString(4, b.getCategory());
			statement.setString(5, b.getPrice());
			statement.setString(6, b.getDescription());
			statement.setString(7, b.getIsbn());
			statement.setString(8, b.getReadLink());
			statement.executeUpdate();

			// ---------
		} catch (Exception e) {
			Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	// ----------------------------------------------------------------------------

	// ---------------- Them user,book vao database ----------------
	public void addUser_Book(int user_id, int book_id) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into users_books(user_id, book_id) values(?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, user_id);
			statement.setInt(2, book_id);
			statement.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// -------------------------- Xoa sach khoi database --------------------------
	public void RmvBook(String isbn) {
		DBUtils db = DBUtils.getInstance();
		String sql = "DELETE from books where isbn=?";
		// kiem tra truong hop xoa sach thi phai xoa cac du lieu tu cac bang khac lien
		// quan
		// toi sach do
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			String id1 = isbn;
			statement.setString(1, id1);
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// ---------------------------------------------------`--------------------------
	public boolean checkAuthor_notexists(int thisid) {
		ArrayList<Integer> list_author_id = new ArrayList<Integer>();
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from author";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list_author_id.add(rs.getInt("author_id"));
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
		for (int i : list_author_id) {
			if (thisid == i) {
				return false;
			}
		}
		return true;
	}

	public int getNextBookId() {
		int currentid = 0;
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from books";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				currentid++;
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
		return currentid;
	}

	public int getNextAuthorId() {
		int currentid = 0;
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from author";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				currentid++;
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
		return currentid;
	}

}

// them chuc nang tim sach ko theo api (tim dua tren cac column: category, name, author, isbn,...) trong database
