package gg;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GgDesktop {
    public static void main (String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Gg project";
        config.vSyncEnabled = true;
        config.useGL20 = true;
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new Gg(), config);
    }
}
