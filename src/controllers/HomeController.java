package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import config.Config;
import views.MainWindow;

public class HomeController {
	private MainWindow view;

	private UserController userController;
	private ConfigController configController;
	private ScoreController scoreController;
	private ItemController itemController;
	private StatusController statusController;
	private NpcController npcController;
	private UserInventoryController inventoryController;

	public HomeController(MainWindow view) {
		this.view = view;

		loadWindowPreferences();
		registerListeners();

	}

	public void registerListeners() {
		if (view.mItemExit != null) {
			view.mItemExit.addActionListener(e -> handleClose());
		}

		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});

		if (view.homeBtn != null) {
			view.homeBtn.addActionListener(e -> {
				view.showView(MainWindow.HOME);
				updateMenuState(MainWindow.HOME);
			});
		}

		if (view.usersBtn != null) {
			view.usersBtn.addActionListener(e -> {
				showUsers();
			});
		}

		if (view.configBtn != null) {
			view.configBtn.addActionListener(e -> {
				showConfigs();
			});
		}

		if (view.scoreBtn != null) {
			view.scoreBtn.addActionListener(e -> {
				showScores();
			});
		}

		if (view.itemBtn != null) {
			view.itemBtn.addActionListener(e -> {
				showItems();
			});
		}

		if (view.statusBtn != null) {
			view.statusBtn.addActionListener(e -> {
				showStatuses();
			});
		}

		if (view.npcBtn != null) {
			view.npcBtn.addActionListener(e -> {
				showNpcs();
			});
		}

		if (view.inventoryBtn != null) {
			view.inventoryBtn.addActionListener(e -> {
				showInventories();
			});
		}

		view.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveWindowPreferences();
				handleClose();
			}
		});
	}

	private void showUsers() {
		if (userController == null) {
			userController = new UserController(view.usersPanel);
		}
		userController.loadUsers();
		view.showView(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
	}

	private void showConfigs() {
		if (configController == null) {
			configController = new ConfigController(view.configPanel);
		}
		configController.loadConfigs();
		view.showView(MainWindow.CONFIG);
		updateMenuState(MainWindow.CONFIG);
	}

	private void showScores() {
		if (scoreController == null) {
			scoreController = new ScoreController(view.scorePanel);
		}
		scoreController.loadScores();
		view.showView(MainWindow.SCORE);
		updateMenuState(MainWindow.SCORE);
	}

	private void showItems() {
		if (itemController == null) {
			itemController = new ItemController(view.itemPanel);
		}
		itemController.loadItems();
		view.showView(MainWindow.ITEM);
		updateMenuState(MainWindow.ITEM);
	}

	private void showStatuses() {
		if (statusController == null) {
			statusController = new StatusController(view.statusPanel);
		}
		statusController.loadStatuses();
		view.showView(MainWindow.STATUS);
		updateMenuState(MainWindow.STATUS);
	}

	private void showNpcs() {
		if (npcController == null) {
			npcController = new NpcController(view.npcPanel);
		}
		npcController.loadNpcs();
		view.showView(MainWindow.NPC);
		updateMenuState(MainWindow.NPC);
	}

	private void showInventories() {
		if (inventoryController == null) {
			inventoryController = new UserInventoryController(view.inventoryPanel);
		}
		inventoryController.loadUserInventories();
		view.showView(MainWindow.INVENTORY);
		updateMenuState(MainWindow.INVENTORY);
	}

	private void handleClose() {
		view.dispose();
	}

	private void updateMenuState(String viewName) {
		view.homeBtn.setEnabled(!viewName.equals(MainWindow.HOME));
		view.usersBtn.setEnabled(!viewName.equals(MainWindow.USERS));
		view.configBtn.setEnabled(!viewName.equals(MainWindow.CONFIG));
		view.scoreBtn.setEnabled(!viewName.equals(MainWindow.SCORE));
		view.itemBtn.setEnabled(!viewName.equals(MainWindow.ITEM));
		view.statusBtn.setEnabled(!viewName.equals(MainWindow.STATUS));
		view.npcBtn.setEnabled(!viewName.equals(MainWindow.NPC));
		view.inventoryBtn.setEnabled(!viewName.equals(MainWindow.INVENTORY));
	}

	private void saveWindowPreferences() {
		Dimension size = view.getSize();
		Point point = view.getLocation();

		Config.set("registration.window.width", String.valueOf(size.width));
		Config.set("registration.window.height", String.valueOf(size.height));
		Config.set("registration.window.x", String.valueOf(point.x));
		Config.set("registration.window.y", String.valueOf(point.y));

	}

	private void loadWindowPreferences() {
		int width = Integer.parseInt(Config.get("registration.window.width", "500"));
		int height = Integer.parseInt(Config.get("registration.window.height", "500"));
		String xValue = Config.get("registration.window.x", "");
		String yValue = Config.get("registration.window.y", "");

		if (!xValue.isBlank() && !yValue.isBlank()) {
			view.setWindowLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		} else {
			view.setLocationRelativeTo(null);
		}

		view.setWindowSize(width, height);
	}
}