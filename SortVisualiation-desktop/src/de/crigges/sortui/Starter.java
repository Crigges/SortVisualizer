package de.crigges.sortui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Starter {
	public static final LwjglApplicationConfiguration cfg = init();
	
	public static final LwjglApplicationConfiguration init() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SortVisualiation";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		return cfg;
	}
	
	public static void createSortVisualizer(ApplicationListener l){
		new LwjglApplication(l, cfg);
	}
	
	
}
