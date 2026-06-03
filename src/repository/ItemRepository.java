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
import models.Item;

public class ItemRepository {

	public void save(Item item) throws IOException {
		add(item);
	}

	public List<Item> getItems() throws IOException {
		List<Item> items = new ArrayList<Item>();

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM item");) {

			while (rs.next()) {
				Item item = new Item(rs.getInt("id_item"), rs.getString("item_name"), rs.getString("item_type"),
						rs.getString("description"));
				items.add(item);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return items;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM item WHERE id_item = ?";

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

	public boolean add(Item item) {
		String sql = "INSERT INTO item (item_name, item_type, description) VALUES (?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, item.getItemName());
			pst.setString(2, item.getItemType());
			pst.setString(3, item.getDescription());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Item added successfully");
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean update(int index, Item updatedItem) throws IOException {
		String sql = "UPDATE item SET item_name = ?, item_type = ?, description = ? WHERE id_item = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, updatedItem.getItemName());
			pst.setString(2, updatedItem.getItemType());
			pst.setString(3, updatedItem.getDescription());
			pst.setInt(4, updatedItem.getIdItem());

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