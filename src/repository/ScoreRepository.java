package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Score;

public class ScoreRepository {

	public void save(Score score) throws IOException {
		add(score);
	}

	public List<Score> getScores() throws IOException {
		List<Score> scores = new ArrayList<Score>();

		String sql = "SELECT s.id_score, s.id_user, s.best_score, s.last_score, u.nickname " + "FROM score s "
				+ "INNER JOIN user_cinerea u ON s.id_user = u.id_user_cinerea";

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				Score score = new Score(rs.getInt("id_score"), rs.getInt("id_user"), rs.getInt("best_score"),
						rs.getInt("last_score"));

				// inyectamos el nombre del usuario
				score.setNickname(rs.getString("nickname"));

				scores.add(score);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return scores;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM score WHERE id_score = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows > 0) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean add(Score score) {
		String sql = "INSERT INTO score (id_user, best_score, last_score) VALUES (?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, score.getIdUser());
			pst.setInt(2, score.getBestScore());
			pst.setInt(3, score.getLastScore());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Score added successfully");
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean update(int index, Score updatedScore) throws IOException {
		String sql = "UPDATE score SET id_user = ?, best_score = ?, last_score = ? WHERE id_score = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, updatedScore.getIdUser());
			pst.setInt(2, updatedScore.getBestScore());
			pst.setInt(3, updatedScore.getLastScore());
			pst.setInt(4, updatedScore.getIdScore());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
}