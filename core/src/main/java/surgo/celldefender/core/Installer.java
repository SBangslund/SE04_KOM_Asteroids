/*
 * Created by Samuel Bangslund, Odense SDU Software Engineering 1. semester.
 */
package surgo.celldefender.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        System.out.println("Installing Core");
        LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = "Cell Defender";
		cfg.width = 1000;
		cfg.height = 800;
		cfg.useGL30 = false;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
    }

}
