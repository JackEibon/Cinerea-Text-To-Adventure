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
import models.EntityStatus;

public class EntityStatusRepository {

	public void save(EntityStatus entityStatus) throws IOException {
		add(entityStatus);
	}
	
	public List<EntityStatus> getEntityStatuses() throws IOException {
		List<EntityStatus> entityStatuses = new ArrayList<EntityStatus>();
		
		try(
				Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM entity_status"); 
			) {
				
				while(rs.next()) {
					EntityStatus entityStatus = new EntityStatus(
						rs.getInt("id_entity_status"), 
						(Integer) rs.getObject("id_user"), 
						(Integer) rs.getObject("id_npc"),
						rs.getInt("id_status_effect")
					);
					entityStatuses.add(entityStatus);
				}
				
			}catch(SQLException ex ) {
				ex.printStackTrace();
			}
		
		return entityStatuses;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM entity_status WHERE id_entity_status = ?";
		
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
	
	public boolean add(EntityStatus entityStatus) {
		String sql = "INSERT INTO entity_status (id_user, id_npc, id_status_effect) VALUES (?,?,?)"; 
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setObject(1, entityStatus.getIdUser(), java.sql.Types.INTEGER);
			pst.setObject(2, entityStatus.getIdNpc(), java.sql.Types.INTEGER);
			pst.setInt(3, entityStatus.getIdStatusEffect());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("EntityStatus added successfully");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int index, EntityStatus updatedEntityStatus) throws IOException {
		String sql = "UPDATE entity_status SET id_user = ?, id_npc = ?, id_status_effect = ? WHERE id_entity_status = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setObject(1, updatedEntityStatus.getIdUser(), java.sql.Types.INTEGER);
			pst.setObject(2, updatedEntityStatus.getIdNpc(), java.sql.Types.INTEGER);
			pst.setInt(3, updatedEntityStatus.getIdStatusEffect());
			pst.setInt(4, updatedEntityStatus.getIdEntityStatus());
			
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