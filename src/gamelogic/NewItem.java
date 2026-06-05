package gamelogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Item;
import config.DatabaseConnection;

public class NewItem {
	
	public static Item itemFromDB(String name) throws IOException {
		Item item= null;

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM item WHERE item_name = " + name);) 
		{
			item= new Item(rs.getInt("id_item"), rs.getString("item_name"),
						rs.getString("description"),rs.getString("item_tags"));
			} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return item;
	}
	
	public static Item itemFromDB(int id) throws IOException {
		Item item= null;

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM item WHERE id_item = " + id);) 
		{
			item= new Item(rs.getInt("id_item"), rs.getString("item_name"),
						rs.getString("description"),rs.getString("item_tags"));
			} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return item;
	}
	
	public static Item nothing() {return new Item(
				0,//id
				"Nothing",//command name
				"", //full name
				""//description
				);}
		
	/*Local Item generation*/
	
	
	public static Item sword() {
		Item x=new Item(
				4,//id
				"sword",//command name
				"A sword eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "rusted","weapon","sword")));	
		return x;}
	
	public static Item spear() {
		Item x=new Item(
				8,//id
				"spear",//command name
				"A spear eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "spear,weapon,rusted,pole,reach,thrown,melee")));	
		return x;}
	
	public static Item gun() {
		Item x=new Item(
				11,//id
				"gun",//command name
				"A gun eaten by time and air.",//description
				 new ArrayList<>(List.of(
				                "gun,weapon,projectile,ranged,gunpowder,rusted")));	
		return x;}
		

	
	
}
