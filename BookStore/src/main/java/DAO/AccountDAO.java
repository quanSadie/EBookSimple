package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DBConnect.DBUtils;
import Model.Account;

public class AccountDAO {

	// dang nhap nguoi dung
	public boolean login(String username, String password) {
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
					return true;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return false;
	}

	// dang ky nguoi dung
	public boolean signUpUser(String em, String un, String pw, int ri, String name, int sa) {

		ri = 0;
		DBUtils db = DBUtils.getInstance();
		String sql = "insert into users(userEmail, username,password,role,fullname, statusAcc) values(?,?,?,?,?,?);";
		Connection con = null;
		if (checkUsername(un) == true) {
			try {
				con = db.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, em);
				statement.setString(2, un);
				statement.setString(3, pw);
				statement.setInt(4, ri);
				statement.setString(5, name);
				statement.setInt(6, sa);
				statement.executeUpdate();
				return true;
			} catch (Exception e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			} finally {
				try {
					DBUtils.closeConnection(con);
				} catch (SQLException e) {
					Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return false;

	}

	// hien thi thong tin nguoi dung
	public ArrayList<Account> displayUsers() {
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		ArrayList<Account> uList = new ArrayList<Account>();
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				uList.add(new Account(rs.getString("id"), rs.getString("userEmail"), rs.getString("username"),
						rs.getString("password"), rs.getInt("role"), rs.getString("fullname"), rs.getInt("statusAcc")));
			}
		} catch (Exception e) {
			Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return uList;
	}

	// ham dung de check xem co trung ten dang nhap khi dang ky hay khong
	public boolean checkUsername(String un) {
		DBUtils db = DBUtils.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(un)) {
					return false;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return true;
	}

	// xoa nguoi dung
	public void rmvUser(String id) {
		DBUtils db = DBUtils.getInstance();
		String sql = "DELETE from users where id=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			int id1 = Integer.parseInt(id);
			statement.setInt(1, id1);
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// cap nhat thong tin cua nguoi dung
	public void updUser(String s1, String s2, int s3) {
		DBUtils db = DBUtils.getInstance();
		String sql = "Update users SET fullname=?,password=?,statusAcc=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement = con.prepareStatement(sql);
			statement.setString(1, s1);
			statement.setString(2, s2);
			statement.setInt(3, s3);
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
