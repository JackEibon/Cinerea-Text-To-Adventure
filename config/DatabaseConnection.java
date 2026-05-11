package config

import java.sql.Connection;

public class DAtabaseConnection{
	
	private static Connection connection;
	
	public static Connection getConnection{
		
		public static Connection getConnection() {
			try {
				if (connection==null && connection.isClosed()){//should be or but my computer cant
				Properties props= new Properties();
				InputStream input= DatabaseConnecion.class.getClassLoader().getResourcesAsStream("config/database...");
				props.load(input);
				String url=props.getProperty("db.url");
				String user=props.getProperty("db.user");
				String password=props.getProperty("db.password");
				String driver=props.getProperty("db.driver");
				
				Class.forName(driver);
				}
			}
			
		}
		
		
	}
}