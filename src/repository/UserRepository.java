package repository;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;
import models.User;

public class UserRepository {

	
	
	public void save(User user) throws IOException {
		
		List<User> users = getUsers();
		users.add(user);
		
	}
	
	public List<User> getUsers() throws IOException {
		
		List<User> users = new ArrayList<User>();
		
		try(
				Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM user_cinerea"); 
			) {
				
				while(rs.next()) {
					
					User user = new User(
						rs.getInt("id_user_cinerea"), 
						rs.getString("nickname"), 
						rs.getString("email"),
						rs.getString("gem"),
						rs.getString("weapon"),
						rs.getString("elements"),
						rs.getString("role_cinerea")
					);
					users.add(user);
				}
				
			}catch(SQLException ex ) {
				ex.printStackTrace();
			}
		
		return users;
	}
	
	public boolean delete(int id) {
		
		String sql = "DELETE FROM user_cinerea WHERE id_user_cinerea = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("It has been deleted");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	

	public boolean add(User user) {
		//si usamos AUTO Increment, se queda asi, si no, se reemplaza por aquello que diga /*No Auto_increment*/:
		/*No Auto_increment*/
		//String sql = "INSERT INTO user_cinerea "+ "(inickname, email, gem, weapon, elements, role_cinerea)+ "VALUES (?,?,?,?,?,?,?)"; "
		String sql = "INSERT INTO user_cinerea "
				+ "(id_user_cinerea, nickname, email, gem, weapon, elements, role_cinerea) "
				+ "VALUES (?,?,?,?,?,?)"; //experimentando con esto. Lo vi en un documento y en un tutorial, se supone es mas a salvo y previene SQL injection. No estoy del todo seguro como funciona, asi que si no funciona, se reemplaza
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			/*No Auto_increment*/
			/*
			pst.setString(1, user.getId());
			pst.setString(2, user.getNickname());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getGem());
			pst.setString(5, user.getWeapon());
			pst.setString(6, user.getElements());
			pst.setString(7, user.getRole_cinerea());
			 * 
			 * */
			pst.setString(1, user.getNickname());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getGem());
			pst.setString(4, user.getWeapon());
			pst.setString(5, user.getElements());
			pst.setString(6, user.getRole_cinerea());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("User added successfully");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean update(int index, User updatedUser) throws IOException {
		String sql = "UPDATE user_cinerea SET nickname = ?, email = ?, gem = ?, weapon = ?, elements = ?, role_cinerea = ? WHERE id_user_cinerea = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, updatedUser.getNickname());
			pst.setString(2, updatedUser.getEmail());
			pst.setString(3, updatedUser.getGem());
			pst.setString(4, updatedUser.getWeapon());
			pst.setString(5, updatedUser.getElements());
			pst.setString(6, updatedUser.getRole_cinerea());
			pst.setInt(7, updatedUser.getId());
			
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