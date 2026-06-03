package controllers;

import utils.ThemeManager;
import views.SettingsView;

public class SettingsController {

	private SettingsView view;

	public SettingsController(SettingsView view) {
		this.view = view;
		registerListeners();
	}

	private void registerListeners() {
		view.getBtnLight().addActionListener(e -> {
			ThemeManager.apply("light");
			view.applyThemeColors();
		});

		view.getBtnDark().addActionListener(e -> {
			ThemeManager.apply("dark");
			view.applyThemeColors();
		});
	}
}