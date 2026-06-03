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
import models.Npc;

public class NpcRepository {

	public void save(Npc npc) throws IOException {
		add(npc);
	}

	public List<Npc> getNpcs() throws IOException {
		List<Npc> npcs = new ArrayList<Npc>();

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM npc");) {

			while (rs.next()) {
				Npc npc = new Npc(rs.getInt("id_npc"), rs.getString("npc_name"), rs.getString("npc_role"),
						rs.getString("location"));
				npcs.add(npc);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return npcs;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM npc WHERE id_npc = ?";

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

	public boolean add(Npc npc) {
		String sql = "INSERT INTO npc (npc_name, npc_role, location) VALUES (?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, npc.getNpcName());
			pst.setString(2, npc.getNpcRole());
			pst.setString(3, npc.getLocation());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Npc added successfully");
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean update(int index, Npc updatedNpc) throws IOException {
		String sql = "UPDATE npc SET npc_name = ?, npc_role = ?, location = ? WHERE id_npc = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, updatedNpc.getNpcName());
			pst.setString(2, updatedNpc.getNpcRole());
			pst.setString(3, updatedNpc.getLocation());
			pst.setInt(4, updatedNpc.getIdNpc());

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