package org.jibin.urlshortner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ShortenURL {

	public Connection getConnection() throws IllegalAccessException,
			ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/url_short";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, "root", "root");
		return conn;
	}

	public String getShort(String servername, int port, String contextPath,
			String longUrl) throws Exception {
		Connection conn = null;
		Statement st = null;
		String code = getCode(longUrl);
		if (code == null) {
			code = generateNewCode();
			String insertQry = "INSERT INTO urls(longurl, code) values ('"
					+ longUrl.trim() + "','" + code + "')";
			try {
				conn = getConnection();
				st = conn.createStatement();
				st.execute(insertQry);
			} finally {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		} else {
			// record already exists in the database. Return the existing code.
		}
		return "http://" + servername + ":" + port + contextPath + "/1/" + code;
	}

	public String getCode(String longURL) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;

		String query = "SELECT code FROM urls WHERE longurl='" + longURL.trim()
				+ "'";
		String code = null;
		try {
			try {
				conn = getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(query);

				if (rs.next()) {
					code = rs.getString("code");
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	public String generateNewCode() {
		final int CODE_MAX_LENGTH = 6; // Generates a alphanumeric code of 6
										// characters
		String alphabet = new String(
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		int n = alphabet.length();
		String code = new String();
		Random r = new Random();
		for (int i = 0; i < CODE_MAX_LENGTH; i++)
			code = code + alphabet.charAt(r.nextInt(n));
		return code;
	}

	public String getLongUrl(String code) throws Exception {
		if (code.startsWith("/")) {
			code = code.replace("/", "");
		}
		String query = "SELECT longurl FROM urls WHERE code = '" + code + "'";
		String longurl = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				longurl = rs.getString("longurl");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return longurl;
	}

}
