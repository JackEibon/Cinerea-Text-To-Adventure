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
import models.Status;

public class StatusRepository {

	public void save(Status status) throws IOException {
		add(status);
	}
	
	public List<Status> getStatuses() throws IOException {
		List<Status> statuses = new ArrayList<Status>();
		
		try(
				Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM status_effect"); 
			) {
				
				while(rs.next()) {
					Status status = new Status(
						rs.getInt("id_status_effect"), 
						rs.getString("effect_name"), 
						rs.getInt("duration")
					);
					statuses.add(status);
				}
				
			}catch(SQLException ex ) {
				ex.printStackTrace();
			}
		
		return statuses;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM status_effect WHERE id_status_effect = ?";
		
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
	
	public boolean add(Status status) {
		String sql = "INSERT INTO status_effect (effect_name, duration) VALUES (?,?)"; 
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, status.getEffectName());
			pst.setInt(2, status.getDuration());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Status added successfully");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int index, Status updatedStatus) throws IOException {
		String sql = "UPDATE status_effect SET effect_name = ?, duration = ? WHERE id_status_effect = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, updatedStatus.getEffectName());
			pst.setInt(2, updatedStatus.getDuration());
			pst.setInt(3, updatedStatus.getIdStatus());
			
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