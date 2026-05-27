package utils;

import java.io.*;
import java.util.Properties;

public class WindowPreferences {
    private static final String PATH = "data/app.properties";

    public static void save(int width, int height, int x, int y) {
        Properties props = load();
        props.setProperty("window.width",    String.valueOf(width));
        props.setProperty("window.height",   String.valueOf(height));
        props.setProperty("window.x",        String.valueOf(x));
        props.setProperty("window.y",        String.valueOf(y));
        try (OutputStream out = new FileOutputStream(PATH)) {
            props.store(out, "Window preferences");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties load() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(PATH)) {
            props.load(in);
        } catch (IOException ignored) {}
        return props;
    }
}