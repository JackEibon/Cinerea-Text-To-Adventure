package assets.utils;

import java.awt.Font;

public class AppFont {
	
	private static Font base;
	
	static {
		try {
			base = Font.createFont(Font.TRUETYPE_FONT, AppFont.class.getResourceAsStream("/assets/fonts/OwreKynge.ttf"));
		}catch(Exception e) {
			System.out.println("Not loading...");
			base = new Font("Arial", Font.PLAIN, 14);
		}
	}
	
	private static Font secondary;
	
	static {
		try {
			secondary = Font.createFont(Font.TRUETYPE_FONT, AppFont.class.getResourceAsStream("/assets/fonts/Alkhemikal.ttf"));
		}catch(Exception e) {
			System.out.println("Not loading...");
			secondary = new Font("Arial", Font.PLAIN, 14);
		}
	}
	
	public static Font normal() {
		return base.deriveFont(28f);
	}
	
	public static Font title() {
		return base.deriveFont(42f);
	}
	
	public static Font normalSecondary() {
		return secondary.deriveFont(22f);
	}
	
	public static Font titleSecondary() {
		return secondary.deriveFont(30f);
	}
}
