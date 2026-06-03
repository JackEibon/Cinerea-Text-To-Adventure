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
import models.Config;

public class ConfigRepository {

	public void save(Config config) throws IOException {
		add(config);
	}
	
	public List<Config> getConfigs() throws IOException {
		List<Config> configs = new ArrayList<Config>();
		
		try(
				Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM configuration"); 
			) {
				
				while(rs.next()) {
					Config config = new Config(
						rs.getInt("id_config"), 
						rs.getInt("id_user"), 
						rs.getString("setting_key"),
						rs.getString("setting_value")
					);
					configs.add(config);
				}
				
			}catch(SQLException ex ) {
				ex.printStackTrace();
			}
		
		return configs;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM configuration WHERE id_config = ?";
		
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
	
	public boolean add(Config config) {
		String sql = "INSERT INTO configuration (id_user, setting_key, setting_value) VALUES (?,?,?)"; 
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, config.getIdUser());
			pst.setString(2, config.getSettingKey());
			pst.setString(3, config.getSettingValue());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Config added successfully");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int index, Config updatedConfig) throws IOException {
		String sql = "UPDATE configuration SET id_user = ?, setting_key = ?, setting_value = ? WHERE id_config = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, updatedConfig.getIdUser());
			pst.setString(2, updatedConfig.getSettingKey());
			pst.setString(3, updatedConfig.getSettingValue());
			pst.setInt(4, updatedConfig.getIdConfig());
			
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