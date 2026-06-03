package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DatabaseConnection;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {

	public User login(String email, String password) {

		/*
		 * String sql = "SELECT id, email, password FROM users WHERE email = '" + email
		 * + "' AND password = '" + password + "'";
		 */

		String sql = "SELECT id_user_cinerea, email, wordpass, role_cinerea, nickname FROM user_cinerea WHERE email = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				String hashedPassword = rs.getString("wordpass");

				boolean correctPassword = PasswordUtils.checkPassword(password, hashedPassword);

				if (!correctPassword)
					return null;

				User user = new User();
				user.setId(rs.getInt("id_user_cinerea"));
				user.setEmail(rs.getString("email"));
				user.setNickname(rs.getString("nickname"));
				user.setRole_cinerea(rs.getString("role_cinerea"));

				return user;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
