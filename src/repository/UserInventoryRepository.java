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
import models.UserInventory;

public class UserInventoryRepository {

	public void save(UserInventory userInventory) throws IOException {
		add(userInventory);
	}
	
	public List<UserInventory> getUserInventories() throws IOException {
		List<UserInventory> inventories = new ArrayList<UserInventory>();
		
		try(
				Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM user_inventory"); 
			) {
				
				while(rs.next()) {
					UserInventory inventory = new UserInventory(
						rs.getInt("id_inventory"), 
						rs.getInt("id_user"), 
						rs.getInt("id_item"),
						rs.getInt("quantity")
					);
					inventories.add(inventory);
				}
				
			}catch(SQLException ex ) {
				ex.printStackTrace();
			}
		
		return inventories;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM user_inventory WHERE id_inventory = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean add(UserInventory userInventory) {
		String sql = "INSERT INTO user_inventory (id_user, id_item, quantity) VALUES (?,?,?)"; 
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, userInventory.getIdUser());
			pst.setInt(2, userInventory.getIdItem());
			pst.setInt(3, userInventory.getQuantity());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("UserInventory added successfully");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int index, UserInventory updatedUserInventory) throws IOException {
		String sql = "UPDATE user_inventory SET id_user = ?, id_item = ?, quantity = ? WHERE id_inventory = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, updatedUserInventory.getIdUser());
			pst.setInt(2, updatedUserInventory.getIdItem());
			pst.setInt(3, updatedUserInventory.getQuantity());
			pst.setInt(4, updatedUserInventory.getIdInventory());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				return true;
			}		
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}