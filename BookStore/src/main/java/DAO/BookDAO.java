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
				list.add(new Book(rs.getString("title"), rs.getString("cover"), rs.getString("price"),
						rs.getString("description"), rs.getString("isbn"), rs.getString("readLink")));
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
						pr + "$", arr.getJSONObject(i).getJSONObject("volumeInfo").getString("description"),
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

	// ---------------- Them tac gia vao database ----------------
	public void addAuthor(String authorName) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into author(author_name) values(?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, authorName);
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

	// ---------------- Them tac gia,book vao database ----------------
	public void addAuthor_Book(int author_id, int book_id) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into authors_books(author_id, book_id) values(?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, author_id);
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

	// ---------------- Them genre,book vao database ----------------
	public void addGenre_Book(int genre_id, int book_id) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into books_genres(genre_id, book_id) values(?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, genre_id);
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

	// ---------------- Them the loai database ----------------
	public void addGenre(String genre) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into genres(genre_name) values(?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, genre);
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

	// -------------------------- Them sach vao database --------------------------
	public void AddBook(String tt, String img, String price, String des, String isbn, String rl) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into books(title,cover,price,description, isbn, readlink) values(?,?,?,?,?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tt);
			statement.setString(2, img);
			statement.setString(3, price);
			statement.setString(4, des);
			statement.setString(5, isbn);
			statement.setString(6, rl);
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

	// ----------------------------------------------------------------------------
	// ----------------------- Them the loai ----------------------

	// (phai dc goi dong thoi luc them sach) them the loai(them du lieu vao bang
	// trung gian giua
	// book va genre)
	public void AddGenre(String bi, String gi) {
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into book_genre(book_id, genre_id) values(?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, bi);
			statement.setString(2, gi);
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
	public void RmvBook(String id) {
		DBUtils db = DBUtils.getInstance();
		String sql = "DELETE from books where id=?";
		// kiem tra truong hop xoa sach thi phai xoa cac du lieu tu cac bang khac lien
		// quan
		// toi sach do
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			String id1 = id;
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
	public boolean checkAuthor_exists(int thisid) {
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

	public int getNextGenreId() {
		int currentid = 0;
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from genres";
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

	public boolean checkGenre_exists(String thisgenre) {
		ArrayList<String> list_genre = new ArrayList<String>();
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from genres";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list_genre.add(rs.getString("genre_name"));
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
		for (String i : list_genre) {
			if (thisgenre.equals(i)) {
				return false;
			}
		}
		return true;
	}
}

// them chuc nang tim sach ko theo api (tim dua tren cac column: category, name, author, isbn,...) trong database
