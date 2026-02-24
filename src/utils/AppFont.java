package utils;

import java.awt.Font;


public class AppFont {
	
	private static Font base;
	
	static {
		try {
			base = Font.createFont(Font.TRUETYPE_FONT, AppFont.class.getResourceAsStream("../font/OwreKynge.ttf"));
		}catch(Exception e) {
			System.out.println("No esta cargando");
			base = new Font("Arial", Font.PLAIN, 14);
		}
	}
	
	public static Font normal() {
		return base.deriveFont(14f);
	}
}
