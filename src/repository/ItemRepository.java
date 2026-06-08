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
				Item item = new Item(rs.getInt("id_item"), rs.getString("item_name"),
						rs.getString("description"),rs.getString("item_tags"));
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
		String sql = "INSERT INTO item (item_name, description, item_tags) VALUES (?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, item.getName());
			pst.setString(2, item.getDescription());
			pst.setString(3, item.getTagList());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean update(int index, Item updatedItem) throws IOException {
		String sql = "UPDATE item SET item_name = ?, description = ?, item_tags = ? WHERE id_item = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, updatedItem.getName());
			pst.setString(2, updatedItem.getDescription());
			pst.setString(3, updatedItem.getTagList());
			pst.setInt(4, updatedItem.getId());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
	
	public List<String> getItemNames() {//used mainly to load images
	    List<String> names = new ArrayList<>();
	    String sql ="SELECT item_name FROM item";
	    try (
	        Connection connection =
	            DatabaseConnection.getConnection();
	        Statement st =
	            connection.createStatement();
	        ResultSet rs =
	            st.executeQuery(sql);
	    ) { while (rs.next()) {
	            names.add(
	                rs.getString("item_name")
	            );
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return names;
	}
}