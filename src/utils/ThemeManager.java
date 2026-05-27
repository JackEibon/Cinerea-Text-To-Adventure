package utils;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import config.Config;

public class ThemeManager {

	// Cada view que tenga colores customizados se registra aqui para cambiarselos
	private static final List<Runnable> themeListeners = new ArrayList<>();

	public static void addThemeListener(Runnable listener) {
		themeListeners.add(listener);
	}

	public static void removeThemeListener(Runnable listener) {
		themeListeners.remove(listener);
	}

	public static void applySavedTheme() {
		String theme = Config.get("ui.theme", "light");
		apply(theme);
	}

	public static void apply(String theme) {
		try {
			if (theme.equalsIgnoreCase("dark")) {
				FlatDarkLaf.setup();
			} else {
				FlatLightLaf.setup();
			}

			Config.set("ui.theme", theme);

			for (Window w : Window.getWindows()) {
				SwingUtilities.updateComponentTreeUI(w);
			}

			// 2. Notifica a cada view para que actualice sus colores hardcodeados
			for (Runnable listener : themeListeners) {
				listener.run();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void toggle() {
		String current = Config.get("ui.theme", "light");
		if (current.equalsIgnoreCase("light")) {
			apply("dark");
		} else {
			apply("light");
		}
	}
}